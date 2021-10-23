# Coteji Gradle Plugin
Apply the plugin like this:
```
plugins {
    id 'io.github.coteji' version '0.2.0'
}
```
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