plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}