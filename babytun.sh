#!/usr/bin/env bash
# base 函数
killbabytun(){
   pid=`ps -ef|grep babytun | grep java|awk '{print $2}'`
    echo "babytun Id list: $pid"
    if [ "$pid" = "" ]
    then
        echo "no babytun pid alive"
    else
        kill -9 $pid
    fi
}
# 进入项目目录
cd $PROJ_PATH/babytun
# 执行maven命令 进行清除及编译打包
mvn clean install
# 停止 tomcat
killbabytun
# 删除所有工程
rm -rf /home/babytun/*
# copy 并 重命名 war 到 webapps 目录下
cp $PROJ_PATH/babytun/target/babytun.jar /home/babytun/
# 启动tomcat
nohup java -jar /home/babytun/babytun.jar >babytun.log &
#防止nohup卡住
jobs
