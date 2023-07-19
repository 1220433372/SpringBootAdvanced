# 指定基础镜像
FROM sunxs.com/library/java:1.0
MAINTAINER Sunxs
ADD app.jar /data
EXPOSE 8081
#入口 Java启动命令
ENTRYPOINT ["java","-jar","app.jar"]