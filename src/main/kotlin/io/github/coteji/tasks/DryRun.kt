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

open class DryRun : CotejiTask() {

    @Option(
        option = "force",
        description = "if provided, all the tests found both in Source and Target (by ID), will be updated from the Source"
    )
    @Input
    var force: Boolean = false

    @TaskAction
    fun dryRun() {
        val forceOption = if (force) " --force" else ""
        runCotejiCommand("dry-run$forceOption")
    }

}
