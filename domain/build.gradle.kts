plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation("javax.inject:javax.inject:1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
}