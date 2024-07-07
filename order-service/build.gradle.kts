//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.google.cloud.tools.jib") version "3.4.0"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

group = "com.tommot"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.named<BootJar>("bootJar") {
	dependsOn("copyAgent")
	archiveFileName = "order-service.jar"
}

tasks.register<Copy>("copyAgent") {
	from(configurations["agent"]) {
		rename("opentelemetry-javaagent-.*\\.jar", "opentelemetry-javaagent.jar")
	}
	into(layout.buildDirectory.dir("agent"))
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
jib{
	from {
		image = "gcr.io/distroless/java17-debian12"
		platforms {
			platform {
				architecture = "amd64"
				os = "linux"
			}
		}
	}

	extraDirectories {
		paths {
			path {
				setFrom(layout.buildDirectory.dir("agent"))
				into = "/otelagent"
			}
		}
	}

	container {
		jvmFlags = listOf(
			"-javaagent:/otelagent/opentelemetry-javaagent.jar"
		)
	}
}

tasks.named("jibDockerBuild"){
	dependsOn("copyAgent")
}