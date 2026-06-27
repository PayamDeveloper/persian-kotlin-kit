plugins {
    kotlin("jvm") version "2.0.20"
    `maven-publish`
}

group = "io.github.payamdeveloper"
version = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}

// Lets JitPack (and anyone else) consume this as a Maven dependency
// straight from a GitHub release tag, with zero extra setup.
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
