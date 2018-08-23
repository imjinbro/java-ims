#! /bin/zsh
JAVA="$JAVA_HOME/java"
JAVA_OPTS="$JAVA -Djava.security.egd=file:/dev/./urandom"

REPO=$HOME/java-ims
TARGET=$REPO/build/libs

cd $REPO
git fetch origin master

origin=$(git rev-parse imjinbro)
remote=$(git rev-parse origin/imjinbro)

if [[ $origin == $remote ]]; then
   exit 0
fi

git merge origin imjinbro

#build gradle
./gradlew build -x test

CURRENT_PID=$(pgrep -f java-ims)

if [ -n $CURRENT_PID ]; then 
    kill -9 $CURRENT_PID
    sleep 5
fi

if [ ! -d $TARGET ]; then
    exit 1
fi

cd $TARGET
java -jar java-ims-*.jar & > /dev/null 2>&1

