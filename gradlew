#!/usr/bin/env sh

set -e

# ------------------------------------------------------------------- 
# Gradle startup script for Unix/Linux/Mac OS X 
# 
# This script is intended to be used to start Gradle (a build tool) 
# on Unix-like operating systems. It will invoke the JVM to execute 
# Gradle and will pass the specified arguments to the JVM. 
# ------------------------------------------------------------------- 

# Find the directory of this script
GRADLE_HOME="$(dirname "$(dirname "$0")")"

# Determine the Java home directory.
if [ -z "$JAVA_HOME" ]; then
    if [ -n "$GRADLE_USER_HOME" ]; then
        JAVA_HOME="${GRADLE_USER_HOME}/.."
    else
        JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64"
    fi
fi

# Execute Gradle with the specified arguments
"$JAVA_HOME/bin/java" -cp "$GRADLE_HOME/lib/*" org.gradle.launcher.GradleDaemon "$@"

