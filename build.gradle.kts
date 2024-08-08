plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    
    // developmentOnly
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.3")

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.9.0")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // lombok
    compileOnly("org.projectlombok:lombok")

    // database
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // S3
    implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
