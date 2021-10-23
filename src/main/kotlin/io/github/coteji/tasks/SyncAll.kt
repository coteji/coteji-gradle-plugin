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
package io.github.coteji.tasks

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

open class SyncAll : CotejiTask() {
    @Input
    @Option(
        option = "force",
        description = "if provided, all the tests found both in Source and Target (by ID), will be updated from the Source"
    )
    var force: Boolean = false

    @Input
    @Option(
        option = "id-update-mode",
        description = "defines what to do with tests without IDs in the Source: " +
                "update (default) - adds the generated ID to the test; " +
                "warning - does nothing, but prints a warning message for each such test; " +
                "error - fails the run, if any such test is found."
    )
    var idUpdateMode: String = "update"

    @TaskAction
    fun syncAll() {
        val forceOption = if (force) " --force" else ""
        runCotejiCommand("sync-all --id-update-mode=$idUpdateMode$forceOption")
    }

}