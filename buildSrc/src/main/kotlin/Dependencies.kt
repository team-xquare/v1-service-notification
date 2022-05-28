object Dependencies {
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    const val SPRING_STARTER = "org.springframework.boot:spring-boot-starter-web"
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"

    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val AWS_MESSAGING = "org.springframework.cloud:spring-cloud-starter-aws-messaging:${DependencyVersions.AWS_MESSAGING}"

    const val QUERYDSL = "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL}"
    const val QUERYDSL_PROCESSOR = "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL}:jpa"

    const val JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val MYSQL_CONNECTOR = "mysql:mysql-connector-java"

    const val MAPSTRUCT = "org.mapstruct:mapstruct:1.4.2.Final"
    const val MAPSTRUCT_PROCESSOR = "org.mapstruct:mapstruct-processor:1.4.2.Final"

    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_VERSION}"
    const val ASSERTJ = "org.assertj:assertj-core:${DependencyVersions.ASSERTJ}"
    const val FIREBASE = "com.google.firebase:firebase-admin:${DependencyVersions.FIREBASE}"


}
