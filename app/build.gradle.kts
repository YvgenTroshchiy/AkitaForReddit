plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("project-report") // ./gradlew dependencyReport
    id("com.github.ben-manes.versions") version "0.39.0" // ./gradlew dependencyUpdates
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.troshchiy.akitaforreddit"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val baseUrl = "BASE_URL"

        debug {
            buildConfigField("String", baseUrl, "\"https://api.reddit.com\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", baseUrl, "\"https://api.reddit.com\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:${Dependencies.composeVersion}")
    implementation("androidx.compose.material:material:${Dependencies.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Dependencies.composeVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Di
    implementation(Dependencies.Di.dagger)
    kapt(Dependencies.Di.daggerCompiler)

    // region Network
    implementation(Dependencies.Network.retrofit2)
    implementation(Dependencies.Network.okhttp3LoggingInterceptor)
    implementation(Dependencies.Network.gson)
    // endregion

    // region Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Dependencies.composeVersion}")
    // endregion
}
