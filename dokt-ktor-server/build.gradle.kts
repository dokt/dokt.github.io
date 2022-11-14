@file:Suppress("UNUSED_VARIABLE")

plugins {
    `maven-publish`
    signing
    kotlin("multiplatform")
}

description = "Dokt Ktor server for defining presentation layer infrastructure."

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
                api(project(":dokt-interface"))
                api(Ktor.server)
                api(Ktor.server.cachingHeaders)
                api(Ktor.server.callLogging)
                api(Ktor.server.contentNegotiation)
                api(Ktor.server.htmlBuilder)
                api(Ktor.server.hostCommon) // for shutdown URL
                api(Ktor.server.resources)
                api("io.ktor:ktor-serialization-kotlinx-json:_")
            }
        }

        val jvmMain by getting {
            dependencies {
                api(Ktor.server.netty)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(Testing.kotest.runner.junit5)
                runtimeOnly("ch.qos.logback:logback-classic:_")
            }
        }
    }
}