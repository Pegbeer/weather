plugins {
    `android-library`
    `kotlin-kapt`
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.pegbeer.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 26


        /*testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")*/
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.jetbrains.kotlinx.coroutines.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core.ktx)

    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.google.dagger.hilt.android)
}