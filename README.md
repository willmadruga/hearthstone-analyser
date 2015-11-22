# README #

### What is this repository for? ###

* Quick tool to keep track on Hearthstone cards, mechanics and stuff
* 0.0.2

### How do I get set up? ###

* git clone https://willmadruga@bitbucket.org/willmadruga/hearthstone-analyser.git
* cd hearthstone-analyser
* mvn package
* mkdir target/data
* cp AllSets.json target/data
* java -jar hearthstone-analyser-0.0.2.jar
* browse http://127.0.0.1:8080/
* browse http://127.0.0.1:8080/console/ to gain access to database console
* use same configuration found in application.properties

### Contribution guidelines ###

* Writing tests
* Code review