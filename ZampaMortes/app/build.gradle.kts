plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.zampamortes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.zampamortes"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.room:room-compiler:2.6.1") {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation("androidx.room:room-common:2.6.1") {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation("androidx.room:room-runtime:2.6.1") {
        exclude(group = "com.intellij", module = "annotations")
    }
    annotationProcessor("androidx.room:room-compiler:2.6.1") {
        exclude(group = "com.intellij", module = "annotations")
    }
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}