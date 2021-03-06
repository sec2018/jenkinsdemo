#!/usr/bin/env bash

export BUILD_ID="dontkillme"

killTomcat()
{
    pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
    echo "tomcat Id list :$pid"
    if [ "$pid"="" ]
    then
        echo "no tomcat alive"
    else
        kill -9 $pid
    fi
}

# pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
# echo "tomcat Id list :$pid"
# killTomcat

cd $PROJ_PATH
chmod a+x -R *
cd jenkinsdemo
mvn clean package -Dmaven.test.skip=true

killTomcat
sleep 3

echo "rm"
rm -rf $TOMCAT_APP_PATH/webapps/jenkinsdemo
rm -f $TOMCAT_APP_PATH/webapps/jenkinsdemo.war

echo "cp"
cp $PROJ_PATH/jenkinsdemo/target/jenkinsdemo-0.0.1-SNAPSHOT.war $TOMCAT_APP_PATH/webapps/jenkinsdemo-0.0.1-SNAPSHOT.war

echo "mv"
cd $TOMCAT_APP_PATH/webapps/
mv jenkinsdemo-0.0.1-SNAPSHOT.war jenkinsdemo.war

cd $TOMCAT_APP_PATH/
sh bin/startup.sh