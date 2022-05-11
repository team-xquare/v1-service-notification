import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = "1.6.21"
    }

    apply {
        plugin("org.jetbrains.kotlin.kapt")
        version = "1.6.10"
    }

    dependencies {
        implementation(Dependencies.KOTLIN_REFLECT)
        implementation(Dependencies.KOTLIN_JDK)
        testImplementation(Dependencies.SPRING_TEST)
        testImplementation(Dependencies.ASSERTJ)
    }
}

allprojects {
    group = "io.github"
    version = "0.0.1-SNAPSHOT"

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
        }

        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }
}