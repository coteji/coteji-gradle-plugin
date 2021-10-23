/*
 *    Copyright (c) 2020 - 2021 Coteji AUTHORS.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package io.github.coteji.plugins

import io.github.coteji.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.FileNotFoundException

class CotejiGradlePlugin : Plugin<Project> {
    private val cliDepNotation = javaClass.classLoader.getResource("cli_dependency.txt")
        ?: throw FileNotFoundException("cli_dependency.txt")

    override fun apply(project: Project) {
        project.plugins.apply("java")
        project.dependencies.add("implementation", cliDepNotation.readText())
        project.extensions.add("coteji", CotejiPluginExtension::class.java)

        project.tasks.register("unzipCoteji", UnzipCoteji::class.java) {
            it.description = "Helper Coteji task. Believe me, you don't need it."
            it.group = "Coteji"
        }

        project.tasks.register("cotejiSyncAll", SyncAll::class.java) {
            it.description = "Pushes all the tests found in the Source, to the Target. " +
                    "Deletes all the tests in the Target that are not present in the Source (match by id)."
            it.group = "Coteji"
            it.dependsOn("unzipCoteji")
        }

        project.tasks.register("cotejiSyncOnly", SyncOnly::class.java) {
            it.description = "Pushes selected by QUERY tests in the Source, to the Target."
            it.group = "Coteji"
            it.dependsOn("unzipCoteji")
        }

        project.tasks.register("cotejiPushNew", PushNew::class.java) {
            it.description = "Finds all tests in the Source without IDs and pushes them to the Target"
            it.group = "Coteji"
            it.dependsOn("unzipCoteji")
        }

        project.tasks.register("cotejiDryRun", DryRun::class.java) {
            it.description = "Emulates the result of syncAll action without actually doing anything, " +
                    "just logs the results to the console."
            it.group = "Coteji"
            it.dependsOn("unzipCoteji")
        }

        project.tasks.register("cotejiTryQuery", TryQuery::class.java) {
            it.description = "Prints the list of tests from the Source found by query"
            it.group = "Coteji"
            it.dependsOn("unzipCoteji")
        }
    }
}

open class CotejiPluginExtension {
    var configFilePath: String = "config.coteji.kts"
}

fun Project.extension() = (this.extensions.findByName("coteji") as CotejiPluginExtension)