plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("jvm") version "2.2.20"
}
group = "org.example"
version = "1.0-SNAPSHOT"

android {
    namespace = "com.example.zenup"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.zenup"
        minSdk = 24
        targetSdk = 36
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}


repositories {
    mavenCentral()
}

dependencies {

    dependencies {
        val nav_version = "2.9.4"

        implementation("androidx.navigation:navigation-compose:$nav_version")
        implementation("androidx.compose.material:material-icons-extended:1.6.8")
        implementation("androidx.compose.ui:ui:1.6.8")
        implementation("androidx.compose.ui:ui-tooling-preview:1.6.8")
        implementation("androidx.compose.material3:material3:1.2.1")
        implementation("androidx.compose.material:material-icons-extended:1.6.8") // Para os ícones
        implementation("androidx.compose.material3:material3:1.2.1")
        debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
        debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")
        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    }
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.foundation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Retrofit

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Testes
    testImplementation(kotlin("test"))

    // Retrofit (client HTTP)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Converter JSON (Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp client + logging
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coroutines (executar chamadas assíncronas com suspend)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

}

kotlin {
    jvmToolchain(17)
}
