package com.sunxs.boot.framework.exception;

import com.sunxs.boot.framework.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 11:58
 * @description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {


    public static Result<String> handle(Throwable exception) {
        if (exception instanceof BusinessException || exception instanceof LoginException) {
            return Result.fail(exception.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = ex.getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fieldError = fieldErrors.get(0);
            String errorMessage = fieldError.getDefaultMessage();
            log.error("参数校验错误" + "：" + errorMessage);
            return Result.fail(errorMessage);
        } else if (exception instanceof HttpMessageNotReadableException) {
            return Result.fail("请求参数解析异常");
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            return Result.fail("请求参数数据类型错误");
        } else if (exception instanceof DuplicateKeyException) {
            return Result.fail("数据违反唯一约束");
        } else if (exception instanceof DataIntegrityViolationException) {
            return Result.fail("数据完整性异常");
        }
        return Result.fail(exception.getMessage());
    }


    /**
     * 全局异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<String> exceptionHandle(Exception exception) {
        log.error("exception:", exception);
        return handle(exception);
    }


    /**
     * 参数校验异常步骤
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result<String> methodArgumentNotValidException(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        return Result.fail(bindingResult.getFieldError().getDefaultMessage());
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 去掉表单请求参数中字符串的前后空格
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
