plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "9.0.0"
}

group = "io.github.mooy1"
version = "MODIFIED"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release = 25
}

tasks.withType<Test> {
    useJUnitPlatform()
    failOnNoDiscoveredTests = false
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly("com.github.slimefun:Slimefun:4.9-UNOFFICIAL")
    implementation("io.github.mooy1:InfinityLib:f784252486")
    compileOnly("com.github.Slimefun.dough:dough-api:cb22e71335")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.14.1")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.41.1")
}

tasks.shadowJar {
    archiveClassifier = ""
    relocate("io.github.mooy1.infinitylib", "io.github.mooy1.infinityexpansion.infinitylib")
    relocate("io.github.bakedlibs.dough", "io.github.mooy1.infinityexpansion.libraries.dough")
    exclude("META-INF/**")
    minimize()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(mapOf("project" to project))
    }
}
