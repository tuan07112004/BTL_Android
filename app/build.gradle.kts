plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.oke"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.oke"
        minSdk = 30
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Firebase BoM – đảm bảo đồng bộ version
    implementation(platform("com.google.firebase:firebase-bom:33.15.0"))
// Firebase Auth
    implementation("com.google.firebase:firebase-auth")

// Firestore (bạn đã có)
    implementation("com.google.firebase:firebase-firestore")

// Firestore KTX (nếu dùng Kotlin)
    implementation("com.google.firebase:firebase-firestore-ktx") // Khuyên dùng

// Các dependency bổ sung (đã có)
    implementation("com.airbnb.android:lottie:3.7.0")
    // bragde
    implementation ("com.nex3z:notification-badge:1.0.4")
    //youtube
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

}