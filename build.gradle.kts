import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val springBootVersion = "3.0.4"
	val springDependencyManagementVersion = "1.1.0"
	val kotlinVersion = "1.7.22"

	id("org.springframework.boot") version springBootVersion
	id("io.spring.dependency-management") version springDependencyManagementVersion
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
}

group = "org.sejin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	val kotlinFakerVersion = "1.13.0"
	val kluentVersion = "1.72"

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("io.github.serpro69:kotlin-faker:$kotlinFakerVersion")
	testImplementation("org.amshove.kluent:kluent:$kluentVersion")
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
