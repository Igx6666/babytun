#!/usr/bin/env bash
# base ����
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
# ������ĿĿ¼
cd $PROJ_PATH/babytun
# ִ��maven���� ���������������a
mvn clean install
# ֹͣ tomcat
killbabytun
# ɾ�����й���
rm -rf /home/babytun/*
# copy �� ������ war �� webapps Ŀ¼��
cp $PROJ_PATH/babytun/target/babytun.jar /home/babytun/
# ����tomcat
nohup java -jar /home/babytun/babytun.jar >babytun.log &
#��ֹnohup��ס
jobs
