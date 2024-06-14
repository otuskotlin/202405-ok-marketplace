pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ok-marketplace-202405"

include("m1l1-first")
include("m1l2-basic")
include("m1l3-func")
include("m1l4-oop")
