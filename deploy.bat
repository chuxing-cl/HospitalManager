@echo off
title Docker安装与部署
echo ================================================
echo     Docker Desktop 安装与 HospitalManager 部署
echo ================================================
echo.

:: 1. 检查是否已安装Docker
where docker >nul 2>&1
if %errorlevel% equ 0 (
    echo [✓] Docker已安装
    goto :BUILD_DOCKER
)

echo [1/4] Docker未安装，准备下载...
echo     正在检查网络连接...

:: 2. 提供下载链接
echo.
echo ================================================
echo     请手动下载Docker Desktop：
echo.
echo     下载地址：
echo     https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe
echo.
echo     安装步骤：
echo     1. 运行安装程序
echo     2. 勾选所有选项
echo     3. 重启电脑
echo     4. 启动Docker Desktop
echo ================================================
echo.
echo 按任意键打开下载页面...
pause >nul
start https://www.docker.com/products/docker-desktop/
echo.
echo 下载安装完成后，重新运行此脚本
pause
exit /b 0

:BUILD_DOCKER
echo [2/4] 检查Docker服务...
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo [×] Docker服务未运行
    echo     请启动Docker Desktop应用
    pause
    exit /b 1
)

echo [3/4] 构建Docker镜像...
docker build -t hospital-manager:1.0 .
if %errorlevel% neq 0 (
    echo [×] 镜像构建失败
    pause
    exit /b 1
)

echo [4/4] 运行容器...
docker stop hospital-app 2>nul
docker rm hospital-app 2>nul
docker run -d -p 8080:8080 --name hospital-app hospital-manager:1.0

echo.
echo ================================================
echo          部署完成！
echo          访问：http://localhost:8080
echo.
echo          常用命令：
echo          查看日志：docker logs hospital-app
echo          停止应用：docker stop hospital-app
echo          重启应用：docker start hospital-app
echo          进入容器：docker exec -it hospital-app /bin/bash
echo ================================================
pause