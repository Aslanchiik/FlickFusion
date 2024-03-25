@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.flickfusion"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flickfusion"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // ViewBindingPropertyDelegate
    // To use only without reflection variants of viewBinding
    implementation(libs.viewbindingpropertydelegate.noreflection.v159)

    //Lifecycle
    // | for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v270)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)

    // Activity version
    implementation(libs.androidx.activity.ktx)

    // Fragments version
    implementation(libs.androidx.fragment.ktx)

    // Kotlin-Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Paging 3
    implementation(libs.androidx.paging.runtime.ktx)

    // Coil
    implementation(libs.coil)

    // Glide
    implementation(libs.glide)
}