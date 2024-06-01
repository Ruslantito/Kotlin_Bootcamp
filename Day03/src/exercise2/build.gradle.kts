
plugins {
//    kotlin("jvm") version "1.9.10"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}


//kotlin {
//    sourceSets["test"].kotlin.srcDirs("src/files")
////    sourceSets {
////        main.kotlin.srcDirs += 'src/main/myKotlin'
////        main.java.srcDirs += 'src/main/myJava'
////    }
//}


//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(17)
}
