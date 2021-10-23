# Coteji Gradle Plugin
## Configuration
Apply the plugin like this:
```groovy
plugins {
    id 'io.github.coteji' version '0.2.0'
}
```
There is only one property to configure - path to the Coteji configuration script:
```groovy
coteji {
    configFilePath = "path/to/config.coteji.kts"
}
```
Default value is `config.coteji.kts` in the project root.
## Coteji tasks
To see all the Coteji tasks run:
```
./gradlew tasks --all
```
Look at the `Coteji tasks` group:
```
Coteji tasks
------------
cotejiDryRun - Emulates the result of syncAll action without actually doing anything, just logs the results to the console
cotejiPushNew - Finds all tests in the Source without IDs and pushes them to the Target
cotejiSyncAll - Pushes all the tests found in the Source, to the Target. Deletes all the tests in the Target that are not present in the Source (match by id)
cotejiSyncOnly - Pushes selected by QUERY tests in the Source, to the Target
cotejiTryQuery - Prints the list of tests from the Source found by query
```
To see the detailed help for each task run:
```
./gradlew help --task cotejiSyncOnly
```
