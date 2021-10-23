plugins {
    kotlin("jvm") version "1.5.31"
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.16.0"
}

version = "0.2.0"
group = "io.github.coteji"

repositories {
    mavenCentral()
}

pluginBundle {
    website = "https://coteji.github.io/"
    vcsUrl = "https://github.com/coteji/coteji-gradle-plugin"
    tags = listOf("coteji", "automated", "testing")
}

gradlePlugin {
    plugins {
        create("coteji") {
            id = "io.github.coteji"
            displayName = "Coteji Plugin"
            description = "Enables to perform Coteji actions in Gradle projects"
            implementationClass = "io.github.coteji.plugins.CotejiGradlePlugin"
        }
    }
}

//publishing {
//    repositories {
//        maven {
//            name = "localPluginRepository"
//            url = uri("../local-plugin-repository")
//        }
//    }
//}

