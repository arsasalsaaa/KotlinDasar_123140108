import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.0.21" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    configure<KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "testImplementation"(kotlin("test"))
        "testImplementation"("org.junit.jupiter:junit-jupiter:5.10.2")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
