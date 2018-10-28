# ShatApp
ShatApp is an application made for corporations to provide an easy and effective way to chat with colleagues.\
It was created as a project for the course TDA367 at Chalmers University of Technology. \
It is not suitable for actual corporate use in its current state since it can't communicate with anyone but itself, and since it has no capabilities of sending messages over a network.
 ## Presets
There are premade Users and Conversations that can be loaded and put into the MainModel upon instantiation. \
Premade Users and Conversations can be viewed in users.json and conversations.json in /json/. \
Standard admin login is admin:123 (username:password) and one of a few regular users is contact:222. \
You can of course edit these in the JSON, alternatively create your own Users/Conversations to input into the MainModel upon instantiation.
## Running ShatApp with the gradle wrapper
ShatApp can be built and run with

```
gradlew build
gradlew run
```

## Generating JaCoCo coverage report
You can generate a JaCoCo coverage report with

```
gradlew test jacocoTestReport
```

The report can be found in its entirety under build/reports/jacoco/test/html/index.html \
You can also view reports for individual classes in their respective folders in build/reports/jacoco/test/html or by simply navigating to them in the index.html.

## Pmd
Can be run with

```
gradlew pmdMain
```
The report can be found in build/reports/pmd. Note that if pmd discovers an issue, building the project will fail.

## Javadoc
Can be generated with

```
gradlew javadoc
```

It is then found in build/docs/javadoc.
## Final report, UML diagrams, Meeting protocols etc
Can be found in Documents.
