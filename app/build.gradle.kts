plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.frd_potabee"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.frd_potabee"
        minSdk = 27
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    // ===== FITUR BARU =====
    implementation("androidx.biometric:biometric:1.1.0")   // Login sidik jari
    implementation("com.google.zxing:core:3.5.4")          // QR code kartu anggota

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}