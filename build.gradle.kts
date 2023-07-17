plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("kapt") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
    java
    id("io.micronaut.application") version "3.7.2"
}

group "org.jamtte123"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("KafkaProducerSimulatorKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "KafkaProducerSimulatorKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

dependencies {
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut:micronaut-validation")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    kapt("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.graalvm.nativeimage:svm")
    runtimeOnly("ch.qos.logback:logback-classic")
}

graalvmNative {
    toolchainDetection.set(false)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("*")
    }
}

//test {
//    useJUnitPlatform()
//}