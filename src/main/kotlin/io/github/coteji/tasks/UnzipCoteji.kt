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

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class UnzipCoteji : DefaultTask() {
    @TaskAction
    fun action() {
        if (!File("${project.buildDir}/coteji-cli").exists()) {
            project.configurations
                .filter { it.isCanBeResolved && it.name !in listOf("archives", "default") }
                .forEach { config ->
                    config.filter { file -> "coteji-cli" in file.path }.forEach { file ->
                        project.copy {
                            it.from(project.zipTree(file as Any))
                            it.into(project.buildDir)
                        }
                    }
                }
            val dirName = project.buildDir.list()?.find { it.startsWith("coteji-cli-") }
            File("${project.buildDir}/$dirName").renameTo(File("${project.buildDir}/coteji-cli"))
        }
    }
}
