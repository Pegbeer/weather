plugins {
    `android-library`
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.pegbeer.usecases"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        /*testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")*/
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            /*proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )*/
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
    implementation(project(":domain"))
    implementation(libs.jetbrains.kotlinx.coroutines.core)
}