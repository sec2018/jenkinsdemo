#!/usr/bin/env bash

killTomcat()
{
    pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
    echo "tomcat Id list :$pid"
    if [ "$pid" = "" ]
    then
        echo "no tomcat pid alive"
    else
        echo "tomcat running at :$pid"
        kill -9 $pid
    fi
}

cd $PROJ_PATH/jenkinsdemo
mvn clean package -Dmaven.test.skip=true

killTomcat
# cd $TOMCAT_APP_PATH/
# sh bin/shutdown.sh

rm -rf $TOMCAT_APP_PATH/webapps/jenkinsdemo
rm -f $TOMCAT_APP_PATH/webapps/jenkinsdemo.war

cp $PROJ_PATH/jenkinsdemo/target/jenkinsdemo-0.0.1-SNAPSHOT.war $TOMCAT_APP_PATH/webapps/jenkinsdemo-0.0.1-SNAPSHOT.war

cd $TOMCAT_APP_PATH/webapps/
mv jenkinsdemo-0.0.1-SNAPSHOT.war jenkinsdemo.war

cd $TOMCAT_APP_PATH/
sh bin/startup.sh