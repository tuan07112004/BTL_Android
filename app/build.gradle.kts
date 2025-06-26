plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.oke"
<<<<<<< HEAD
    compileSdk = 35
=======
    compileSdk = 36
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962

    defaultConfig {
        applicationId = "com.example.oke"
        minSdk = 30
<<<<<<< HEAD
        targetSdk = 35
=======
        targetSdk = 36
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
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
<<<<<<< HEAD
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.credentials)
<<<<<<< HEAD
    implementation(libs.googleid)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


=======
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
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
<<<<<<< HEAD
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    //glider
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
=======
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.0.0")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //glider
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    //RxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

<<<<<<< HEAD
    // PaperDB
    implementation("io.github.pilgr:paperdb:2.7.2")
    implementation ("com.google.firebase:firebase-messaging:23.4.1")

    implementation ("com.github.dhaval2404:imagepicker:2.1")

    // EventBus
    implementation("org.greenrobot:eventbus:3.0.0")
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962

}