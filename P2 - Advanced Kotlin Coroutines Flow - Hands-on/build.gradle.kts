plugins {
    kotlin("jvm") version "2.1.20" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaExec> {
        standardInput = System.`in`
    }
}
