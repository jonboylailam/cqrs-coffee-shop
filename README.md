# Spring Starter

Purpose of this project is to provide a good starting point for building a SprintHive service and
to cover some basic tasks.

## Running

    gradle bootRun
    
    # run with debug
    gradle bootRun --debug-jvm 
    
## Libraries 

[http://projects.spring.io/spring-boot/]()  
[https://projectlombok.org]() - Spice up your java: Automatic Resource Management, 
automatic generation of getters, setters, equals, hashCode and toString, and more!

## Profiles

dev - properties are loaded from application.yaml 
test - properties are loaded from application-test.yaml   
preprod - properties are loaded from application-preprod.yaml  
production - properties are loaded from application-production.yaml

Helper scripts to run the app with different profiles

/bin/runTest.sh - packages the app and runs with the test profile activated
/bin/runPrepod.sh - packages the app and runs with the preprod profile activated
/bin/runProd.sh - packages the app and runs with the prod profile activated

### Override props

You can override props by creating a <project-home>/application.yaml this file is .gitignored

## Logging

@Slf4j
lombrok
logback
fluent 
When to use what level?
Error 
Warning
Info
debug


## Testing

To run an individual test:

    $ gradle test -Dtest.single=PropTest

To debug a test:

    $ gradle test -Dtest.debug=true -Dtest.single=DemoSpec

## Deployment

    # create executable jar see <project-home>/bin/run*.sh for example 
    gradle bootRepackage
