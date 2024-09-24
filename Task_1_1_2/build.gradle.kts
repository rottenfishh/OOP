import org.gradle.testing.jacoco.tasks.JacocoReport
plugins {
    id("java")
    id("jacoco")
}

group = "ru.nsu.kolodina.blackjack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.jetbrains:annotations:16.0.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}