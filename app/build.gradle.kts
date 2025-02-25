plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") // KSP (Para Room)
    id("dagger.hilt.android.plugin") // Hilt
    kotlin("kapt") // KAPT (Para Hilt)
    id("com.google.gms.google-services") // Firebase
    id("kotlin-parcelize")
}

android {
    namespace = "com.kodeleku.mvvm_damd"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kodeleku.mvvm_damd"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    viewBinding{
        enable=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // LifeCycle
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle + Coroutine support
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel with Coroutines
    implementation(libs.androidx.lifecycle.livedata.ktx) // LiveData with Coroutines

    // Coroutines
    implementation(libs.kotlinx.coroutines.core) // Core de las corutinas para Kotlin
    implementation(libs.kotlinx.coroutines.android) // Soporte de corutinas para Android

    // Room (con KSP)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    ksp(libs.androidx.room.compiler) // ksp

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx) // 2.8.7
    implementation(libs.androidx.navigation.ui.ktx) // 2.8.7

    // Hilt (requiere KAPT)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler) // KAPT

    // Retrofit y Picasso
    implementation(libs.retrofit) // Core de Retrofit
    implementation(libs.converter.gson) // Gson para parsear JSON
    implementation(libs.picasso) // Picasso for image loading

    // Firebase
    implementation(platform(libs.firebase.bom)) // Firebase BOM
    implementation(libs.firebase.auth)
    implementation(libs.firebase.auth.ktx) // Firebase Authentication
    implementation(libs.firebase.firestore.ktx) // Firestore (opcional)
    implementation(libs.firebase.firestore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}