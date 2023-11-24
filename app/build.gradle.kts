plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.challangebinar3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.challangebinar3"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        signingConfig
        signingConfigs

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        getByName("release")
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://testing.jasa-nikah-siri-amanah-profesional.com\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("staging"){
            buildConfigField("String", "BASE_URL", "\"https://testing.jasa-nikah-siri-amanah-profesional.com\"")
        }
        create("production"){
            buildConfigField("STRING_FIELD_NAME", "BASE_URL", "https://testing.jasa-nikah-siri-amanah-profesional.com")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //
    implementation("com.google.firebase:firebase-analytics-ktx:21.5.0")
    //
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
    //
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")


    implementation("com.google.firebase:firebase-analytics:21.5.0")
    implementation("com.google.firebase:firebase-crashlytics:18.6.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")

    //View model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    //Room
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //json
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    //picaso
    //implementation("com.squareup.picasso:picasso:2.71828")

    implementation ("io.insert-koin:koin-core:3.5.0")
    implementation ("io.insert-koin:koin-android:3.5.0")
    implementation ("io.insert-koin:koin-android-compat:3.5.0")
    implementation ("io.insert-koin:koin-androidx-workmanager:3.5.0")
    implementation ("io.insert-koin:koin-androidx-navigation:3.5.0")

    testImplementation("io.mockk:mockk-android:1.13.8")
    testImplementation("io.mockk:mockk-agent:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    testImplementation("app.cash.turbine:turbine:1.0.0")
    testImplementation ("androidx.test.ext:junit:1.1.3")
    testImplementation ("androidx.test.ext:truth:1.4.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.mockito:mockito-core:3.11.2")
    testImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("com.google.truth:truth:1.1.5")


}