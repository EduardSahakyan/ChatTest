plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.test.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    // projects
    implementation(project(":domain"))

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.room.paging)

    // serialization
    implementation(libs.kotlin.serialization)

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}