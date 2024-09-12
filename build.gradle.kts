plugins {
    id("org.springframework.boot") version "3.0.0"
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
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // lombok
    compileOnly("org.projectlombok:lombok")

    // database
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // S3
    implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

    //annotation
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // mail
    implementation ("org.springframework.boot:spring-boot-starter-mail:3.1.2")

    // thymeleaf
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation ("org.springframework.boot:spring-boot-starter-web")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
