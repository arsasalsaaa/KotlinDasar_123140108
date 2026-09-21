plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "p3-generics"

include(
    "handson1-latihan",
    "handson1-solusi",
    "handson2-latihan",
    "handson2-solusi",
    "handson3-latihan",
    "handson3-solusi"
)
