import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

buildscript {
	dependencies {
		classpath("io.spring.gradle:dependency-management-plugin:1.0.10.RELEASE")
	}
}


group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
	}
}

dependencies {
	//cucumber
	testImplementation("io.cucumber:cucumber-jvm:7.11.0")
	testImplementation("io.cucumber:cucumber-java:7.11.0")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:7.11.0")
	testImplementation("org.junit.platform:junit-platform-suite")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

//	testImplementation("io.cucumber:cucumber-spring:7.11.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	testImplementation("javax.inject:javax.inject:1")

	//spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")


	//consul
//	implementation("org.springframework.cloud:spring-cloud-starter-consul-all")
	implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
//	implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
//	implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
