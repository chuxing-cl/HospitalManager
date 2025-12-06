# 使用官方更新的基础镜像（推荐JDK11或17用于生产）
FROM openjdk:11-jre-slim

# 维护者信息（LABEL更规范）
LABEL maintainer="hospital-manager"
LABEL description="Hospital Management System"

# 一次性设置环境变量和安装
ENV TZ=Asia/Shanghai \
    CATALINA_HOME=/usr/local/tomcat \
    TOMCAT_VERSION=9.0.94

# 设置时区（更简洁的方式）
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 所有安装操作合并到一层，清理缓存减小镜像体积
RUN apt-get update && apt-get install -y --no-install-recommends \
    wget \
    && wget -q https://downloads.apache.org/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && tar -xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && mv apache-tomcat-${TOMCAT_VERSION} ${CATALINA_HOME} \
    && rm -rf ${CATALINA_HOME}/webapps/* \
    && rm apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && apt-get purge -y --auto-remove wget \
    && rm -rf /var/lib/apt/lists/*

# 复制WAR文件（如果target目录不在构建上下文，需要调整路径）
COPY HospitalManager-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps/ROOT.war

# 暴露端口
EXPOSE 8080

# 健康检查（可选但推荐）
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# 启动命令
CMD ["catalina.sh", "run"]