
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.springframework:spring-context:6.0.8")
    implementation("org.yaml:snakeyaml:2.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
