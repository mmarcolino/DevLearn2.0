import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "br.com.devLearn"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("junit:junit:4.13.1")
	runtimeOnly ("org.postgresql:postgresql")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//implementation("org.flywaydb:flyway-core:8.5.10")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("com.h2database:h2:2.1.210")
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "org.mockito", module = "mockito.core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testImplementation("io.mockk:mockk:1.10.4")
	testImplementation("io.rest-assured:rest-assured:4.4.0")
	//implementation("org.apache.groovy:groovy-bom:4.0.1")

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
