appName=healthy.jar
PID=$(ps -ef | grep $appName | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
echo Application is already stopped
else
echo kill $PID
kill $PID
fi
nohup java -jar $appName --spring.profiles.active=prod &