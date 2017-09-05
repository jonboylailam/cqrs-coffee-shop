#!/bin/sh

gradle clean bootRepackage; java -jar -Dspring.profiles.active=preprod build/libs/starter-service-0.0.1-SNAPSHOT.jar;
