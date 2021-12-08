import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
}

group = "com.bookmarket"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
    //Spring Dependency
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-security:2.5.6")

	//JWT Dependency
	implementation("io.jsonwebtoken:jjwt:0.9.1")

	//DB Dependency
	runtimeOnly("mysql:mysql-connector-java:8.0.27")
	implementation("org.flywaydb:flyway-core:8.0.3")

	//Others Dependency
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")

	//Kotlin Dependency
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Documentation
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("io.springfox:springfox-swagger2:3.0.0")

	//Tests Dependency
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6")
	testImplementation("io.mockk:mockk:1.12.1")
	testImplementation("org.springframework.security:spring-security-test:5.6.0")
	testImplementation("com.h2database:h2:1.4.200")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
