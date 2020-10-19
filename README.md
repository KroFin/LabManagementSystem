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

start.sh

```
#!/bin/sh

RESOURCE_NAME=demo-0.0.1-SNAPSHOT.jar

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
stop.sh

```
#!/bin/sh
RESOURCE_NAME=demo-0.0.1-SNAPSHOT.jar

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

```
restart.sh

```
#!/bin/sh
RESOURCE_NAME=demo-0.0.1-SNAPSHOT.jar

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

(ps：如果你懒，在shell文件夹下我给你写好了，你自己上传运行即可)

(pps：shell代码其实是我抄的，来源：https://blog.csdn.net/whh18254122507/article/details/78011713?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight)

(ppps：别忘了chmod +x start.sh 第一次运行的时候获取一下超级管理员权限)

### 6.如果你使用的是国内代理商的服务器，请在安全组配置中开放相关端口，宝塔面板同理

![port](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/port.png)

### 7.最后在浏览器的地址栏使用你的IP+/端口号访问网页即可

![alldone](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/run%20successed.png)

![ok了家人们](https://github.com/SteveMiller233/LabManagementSystem/blob/master/screenshot/ok.png)

## 项目注解

本项目为前后端分离项目，核心框架为SpingBoot，安全框架为 Apache Shiro，数据持久层为MyBatis，数据库为Mysql，使用的数据连接池为JDBC，相关框架的版本号在上方均有标注，如果对项目依赖有问题，可以在项目的目录中寻找pom文件，即可解惑。

(ps：注释都给家人们写好了，可别再说我没写过注释了。)

### 项目代码预览
```
├─controller(页面控制)
│  ├─APIController(API接口控制器)
│  ├─BillController
│  ├─DeviceController
│  ├─IndexController
│  ├─MatchController
│  ├─PageController
│  └─UserController
├─dao(后端函数方法)
│  ├─BillMapper
│  ├─DeviceMapper
│  ├─RoleMapper(废案)
│  ├─MatchMapper
│  ├─RoleModuleMapper(废案)
│  └─UserDao
├─entity(实体类)
│  ├─Bill
│  ├─DeviceInfo
│  ├─Role(废案)
│  ├─Match
│  ├─RoleModule(废案)
│  └─User
├─Service(服务层接口)
│  ├─BillService
│  ├─DeviceService
│  ├─RoleModuleService(废案)
│  ├─FunctionService(之后的所有非关键的功能函数都会写在这里)
│  ├─MatchService    
│  └─UserService
└─其他
   ├─ShiroConfig(shiro安全框架配置参数)
   └─UserRealm(登陆安全检测参数)
```

### 项目功能
 
* 实验室设备管理
* 实验室人员管理
* 实验室资金记录
* 比赛信息管理
* 设备借入借出记录(计划和终端RFID设备联动，实现真正的物联网实验室)
* 个人信息管理


### 浏览器兼容
|[<img src="https://raw.github.com/alrra/browser-logos/master/src/archive/internet-explorer_9-11/internet-explorer_9-11_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |[<img src="https://raw.github.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera
| --------- | --------- | --------- | --------- | --------- |--------- |
|IE 10+| Edge| last 15 versions| last 15 versions| last 10 versions| last 15 versions

## 作者备注

首先，本项目写的极其简单，适用于javaweb初学者初期学习所用，如果想要更高阶的javaWeb学习项目请参考下面位大神的项目。我的初衷是想模仿下面的这位大佬的，可惜实在是太菜了。

https://github.com/febsteamE

![KroFin](https://blog.krofin.icu/img/code.png)

### KroFin

入门摄影爱好者/自由视频制作者/苦逼程序员/200斤屌丝肥宅

Adobe⭕/ Jetbrains⭕/ Android Studio⭕/ Visual Studio ❌

欢迎关注我的个人推特[KroFin](https://twitter.com/huangkefan233)
 
如果我的代码帮助你完成了作业，请为我点一个关注，或者为我的项目点一颗小星星。
