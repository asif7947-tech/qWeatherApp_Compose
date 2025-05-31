plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    // Add dependency for dagger hilt
    alias(libs.plugins.hilt.android)
    // Add dependency for ksp
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.example.qweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.qweather"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // android core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // compose activity
    implementation(libs.androidx.activity.compose)
    // Compose  UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    // Compose  Material
    implementation(libs.androidx.material3)
    // Splash Screen
    implementation(libs.androidx.splashScreen)
    // Compose Navigation
    implementation(libs.androidx.compose.navigation)
    // Compose Lifecycle State
    implementation(libs.androidx.lifecycle.runtime.compose)
    // Application Lifecycle process
    implementation(libs.androidx.lifecycle.process)
    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)
    // Compose Constraint Layout
    implementation(libs.androidx.constraint.layout.compose)
    // Compose Foundation
    implementation(libs.androidx.compose.foundation)
    // Compose View Model
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Dagger - Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Image Loader coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    // Google Gson Converter for Json
    implementation(libs.google.gson)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.codegen)
    // Location
    implementation(libs.google.play.services.location)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}