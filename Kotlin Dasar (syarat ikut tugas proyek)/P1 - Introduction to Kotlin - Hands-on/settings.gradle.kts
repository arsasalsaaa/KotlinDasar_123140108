pluginManagement {
    plugins {
        kotlin("jvm") version "2.3.10"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "p1-introduction-to-kotlin"

include(
    "handson1-latihan",
    "handson1-solusi",
    "handson2-latihan",
    "handson2-solusi",
    "handson3-latihan",
    "handson3-solusi"
)
