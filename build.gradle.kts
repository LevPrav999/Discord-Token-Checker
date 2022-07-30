import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "ru.levprav"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20180130")
    implementation("com.google.code.gson:gson:2.3.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("Main")
}