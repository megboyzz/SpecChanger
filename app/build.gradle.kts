import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    //id("org.cyclonedx.bom") version "1.8.2"
    id("project-report")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

tasks.register("getDependencies") {
    doLast {
        project.configurations.forEach {
            println(it.name)
            println("\t${it.dependencies.toList()}")
        }
    }
}







android {

    namespace = "com.profile.changer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.profile.changer"
        minSdk = 33
        targetSdk = 33
        versionCode = 12
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    dependenciesInfo {
        // Disables dependency metadata when building APKs.
        includeInApk = true
        // Disables dependency metadata when building Android App Bundles.
        includeInBundle = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "ProfileChanger-${variant.baseName}-${variant.versionName}-${variant.versionCode}.apk"
                output.outputFileName = outputFileName
            }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {

        getByName("debug"){
            storeFile = rootProject.file("platform.keystore")
            storePassword = "android"
            keyAlias = "platform"
            keyPassword = "android"
        }

        create("release"){
            storeFile = rootProject.file("platform.keystore")
            storePassword = "android"
            keyAlias = "platform"
            keyPassword = "android"
        }

    }

}

tasks.register("hello") {
    println("hello!")
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(project(mapOf("path" to ":data")))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.github.alorma:compose-settings-ui-m3:1.0.2")
    implementation("com.github.jitpack:gradle-simple:2.0")

    val voyagerVersion = "1.0.0-rc10"

    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-androidx:$voyagerVersion")


    //implementation("com.github.adrielcafe:voyager:")
    //implementation("com.github.jitpack:gr1adle-simple:2.0")

    implementation(project(mapOf("path" to ":domain", "configuration" to "default")))
    implementation(project(mapOf("path" to ":data", "configuration" to "default")))

    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
}