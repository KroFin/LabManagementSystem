# IOT实验室综合管理系统

## 有关本项目

本项目是一款基于SpringBoot框架的Web端综合管理系统，为本校IOT实验室日常管理之用

## 所用框架

* SpringBoot 2.1.3
* Thymeleaf 3.0.4
* Mybatis 3.5.5
* Mysql 5.5.62
* JDBC 8.0.15
* Shiro 

## 部署步骤

本部分专供不熟悉SpringBoot部署步骤的同学阅读，如果您专精JavaWeb程序设计，请跳过本部分

### 1.由Github 下载本项目到本地，并使用IntelliJ IDEA 2019.1.1及以上的版本打开

![OpenProject](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/OpenProject.png)

### 2.添加相关依赖

### 3.按照图示先点击右侧Maven工具栏，再在Lifecycle下拉菜单中选中图示三个选项，最后点击最上方的绿色按钮打成jar包

![Build](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/Build.png)

### 4.使用相关虚拟机连接软件，将jar包上传至服务器中

(ps：服务器要求必须要有java环境且jdk版本在1.8及以上)

![upload](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/upload.png)

![ServerJDKVersion](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/ServerJDKVersion.png)

### 5.创建start.sh文件，输入下面的shell指令并保存，后再使用./start命令启动项目

```
#!/bin/sh
RESOURCE_NAME=resource-0.0.1-SNAPSHOT.jar
 
tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
echo 'Stop Process...'
kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
echo 'Kill Process!'
kill -9 $tpid
else
echo 'Stop Success!'
fi
 
tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'App is running.'
else
    echo 'App is NOT running.'
fi
 
rm -f tpid
nohup java -jar ./$RESOURCE_NAME --spring.profiles.active=test &
echo $! > tpid
echo Start Success!
```

(ps：如果你懒，在shell文件夹下我给你写好了一个，你自己上传运行即可)

(pps：shell代码其实是我抄的，来源：https://blog.csdn.net/whh18254122507/article/details/78011713?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight)

(ppps：别忘了chmod +x start.sh 第一次运行的时候获取一下超级管理员权限)

### 6.如果你使用的是国内代理商的服务器，请在安全组配置中开放相关端口，宝塔面板同理

![port](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/port.png)

### 7.最后在浏览器的地址栏使用你的IP+/端口号访问网页即可

![alldone](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/run%20successed.png)

![ok了家人们](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/ok.png)

## 项目注解

本项目为前后端分离项目，核心框架为SpingBoot，安全框架为 Apache Shiro，数据持久层为MyBatis，数据库为Mysql，使用的数据连接池为JDBC，相关框架的版本号在上方均有标注，如果对项目依赖有问题，可以在项目的目录中寻找pom文件，即可解惑。
