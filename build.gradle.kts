import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springBootVersion = "3.0.4"
    val springDependencyManagementVersion = "1.1.0"
    val kotlinVersion = "1.7.22"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

allprojects {
    group = "org.sejin"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        val kotlinFakerVersion = "1.13.0"
        val kluentVersion = "1.72"
        val jacksonVersion = "2.14.2"
        val mockkVersion = "1.13.3"
        val springmockkVersion = "4.0.2"

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.querydsl:querydsl-jpa::jakarta")
        kapt("com.querydsl:querydsl-apt::jakarta")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        runtimeOnly("com.mysql:mysql-connector-j")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("io.github.serpro69:kotlin-faker:$kotlinFakerVersion")
        testImplementation("org.amshove.kluent:kluent:$kluentVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("com.ninja-squad:springmockk:$springmockkVersion")
        testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
        testImplementation("com.h2database:h2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
