import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val springBootVersion = "2.3.12.RELEASE"
    implementation("org.springframework.boot:spring-boot-starter-parent:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion") {
        exclude(module = "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot:spring-boot-starter-log4j2:$springBootVersion")
    implementation("com.alibaba:fastjson:1.2.80")

    //implementation("io.projectreactor:reactor-core:3.4.19")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}