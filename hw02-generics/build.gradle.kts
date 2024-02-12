plugins {
    id("java")
}

group = "ru.otus.java.pro"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("ch.qos.logback:logback-classic")
    testImplementation ("org.openjdk.jmh:jmh-core")
    testAnnotationProcessor ("org.openjdk.jmh:jmh-generator-annprocess")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.assertj:assertj-core")
}

tasks.test {
    useJUnitPlatform()
}