import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotzilla)
}

android {
    namespace = "com.api.pokemones"
    compileSdk = 36

    defaultConfig {
        applicationId = "alexdev.api.pokemones"
        minSdk = 28
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.geometry)
    implementation(libs.androidx.compose.foundation.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)

    //HILT
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.androidx.compiler)
    implementation(libs.hilt.android.v248)

    // Hilt para ViewModel (integración con Jetpack)
    implementation(libs.androidx.hilt.navigation.compose)

    // Paginación
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt:coil-gif:2.7.0")
    //icon
    implementation(libs.androidx.compose.material.icons.extended)

    //Skeleton
    implementation(libs.compose.shimmer)

    //sonido
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    //kotzilla
    implementation(libs.kotzilla.sdk.compose)

    //koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)


}

kotzilla {

//    versionName = "1.0.0"
//    keyGeneration = KotzillaKeyGeneration.COMPOSE

    composeInstrumentation = true
}