@file:Suppress("SpellCheckingInspection")

plugins {
    `maven-publish`
    signing
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("io.gitlab.arturbosch.detekt")
}

description = "Common utilities to implement infrastructure services"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(KotlinX.coroutines.core)
                api(project(":dokt-common"))
                api("io.github.oshai:kotlin-logging:_")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":dokt-test"))
                implementation(Testing.kotest.runner.junit5)
                runtimeOnly("ch.qos.logback:logback-classic:_")
            }
        }
    }
}
