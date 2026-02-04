#!/data/data/com.termux/files/usr/bin/bash
DIR=$(cd "$(dirname "$0")" && pwd)
JAVA_HOME=${JAVA_HOME}
exec "$DIR/gradle/bin/gradle" "$@"
