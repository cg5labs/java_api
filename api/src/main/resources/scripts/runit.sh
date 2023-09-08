#!/usr/bin/env bash

#
# global vars
#

PWD=$(pwd)
RUNTIME=$1
PROJECT_ROOT="api"
DOCKER_COMPOSE_UP="/usr/local/bin/docker-compose -f docker-compose-${RUNTIME}.yml up"

#
# functions
#

# Function to handle Ctrl-C
ctrl_c() {
    DOCKER_COMPOSE_DOWN="/usr/local/bin/docker-compose -f $(pwd)/docker-compose-${RUNTIME}.yml down"
    echo "Ctrl-C caught. Exiting..."
    pwd
    eval "${DOCKER_COMPOSE_DOWN}"
    exit 1
}


#
# main
#

# Set up a trap to call the ctrl_c function when Ctrl-C is pressed
trap ctrl_c SIGINT


if [[ "${RUNTIME}" == "tc" ]]; then
  WARFILE="Bookmarks.war"
fi

if [[ "${RUNTIME}" == "jar" ]]; then
  JARFILE="Bookmarks.jar"
fi

if [[ $(basename ${PWD}) != "${PROJECT_ROOT}" ]]; then
    RC=1
    echo "Exiting. Run script only from project-root: ${PROJECT_ROOT}"
    exit $RC
else
    echo "Running in ${PROJECT_ROOT}..."
fi


[[ -z ${REBUILD} ]] && REBUILD="false"

if [[ "$REBUILD" == "true" ]]; then 
    
    # build JAR
    if [[ ! -z $JARFILE ]]; then
      mvn clean package -DskipTests=true
    fi
    
    # build WAR
    if [[ ! -z $WARFILE ]]; then
      mvn clean package -DskipTests=true -Dpackage_type=war
    fi

fi

echo "CWD .."
cd ..
echo
echo "Running ${DOCKER_COMPOSE_UP}"
echo

eval "${DOCKER_COMPOSE_UP}"



