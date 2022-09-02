plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGEMENT
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN
}

val awsMessagingVersion = "2.2.6.RELEASE"

kotlin.sourceSets.main {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

dependencyManagement {
    imports {
        mavenBom(Dependencies.SPRING_CLOUD)
    }
}

dependencies {
    implementation(Dependencies.SPRING_STARTER)
    implementation(Dependencies.SPRING_VALIDATION)
    implementation(Dependencies.SPRING_SECURITY)
    implementation(Dependencies.SPRING_CONFIG)

    implementation(Dependencies.JACKSON)

    implementation(Dependencies.AWS_MESSAGING)

    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_PROCESSOR)

    implementation(Dependencies.QUERYDSL)
    kapt(Dependencies.QUERYDSL_PROCESSOR)

    implementation(Dependencies.JPA)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)

    implementation(Dependencies.OPENFEIGN)

    implementation(Dependencies.FIREBASE)

    implementation(project(":notification-domain"))
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}