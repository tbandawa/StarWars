plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "me.tbandawa.starwars.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "me.tbandawa.starwars.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":data"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")

    // Compose & Livedata
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.material3:material3:1.1.0-alpha03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.paging:paging-compose-android:3.3.0")
    implementation("androidx.paging:paging-common:3.3.0")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.2.0")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-compose:2.6.0-alpha04")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha05")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}