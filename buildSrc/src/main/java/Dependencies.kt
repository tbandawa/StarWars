object Versions {
    val minSdk = 21
    val compileSdk = 30
    val targetSdk = 29
    val buildTools = "30.0.3"
    val versionCode = 1
    val versionName = "1.0"
    val gradle = "4.2.2"
    val kotlin = "1.5.20"
    val ktx = "1.3.1"
    val material = "1.3.0"
    val appcompat = "1.3.0"
    val support = "1.0.0"
    val recyclerview = "1.1.0"
    val lifecycle = "2.2.0"
    val constraint = "2.0.4"
    val navigation = "2.3.0"
    val hilt = "2.37"
    val timber = "4.7.1"
    val retrofit = "2.9.0"
    val okhttp3 = "4.9.0"
    val coroutines = "1.3.9"
    val junit = "4.13.2"
    val mockito = "3.11.2"
    val junit_inst = "1.1.3"
    val espresso_inst = "3.4.0"
}

object Deps {

    // Gradle
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    // Kotlin
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    // AndroidX
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val support = "androidx.legacy:legacy-support-v4:${Versions.support}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val recyclerview =  "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    // Lifecycle
    val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycle_ext = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycle_comp =  "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Navigation
    val navigation_frag_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigation_frag =  "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigation_ui =  "androidx.navigation:navigation-ui:${Versions.navigation}"
    val navigation_dyn = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    val navigation_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    // Hilt
    val hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_comp = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // Material
    val material = "com.google.android.material:material:${Versions.material}"

    // Retrofit
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter_gson =  "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // Coroutines
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Timber
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

}