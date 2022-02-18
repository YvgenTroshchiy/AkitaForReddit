import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")  // remove .kotiln because of https://stackoverflow.com/a/67681697/3825816
    id("project-report") // ./gradlew dependencyReport
    id("com.github.ben-manes.versions") version "0.42.0" // ./gradlew dependencyUpdates
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
        kotlinCompilerExtensionVersion = Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.core))

    implementation(Android.material)
    implementation(Android.appcompat)

    implementation(Compose.core)
    implementation(Compose.ui)
    implementation(Compose.material)
    debugImplementation(Compose.tooling)
    implementation(Compose.toolingPreview)
    implementation(Compose.lifecycle)
    implementation(Compose.activity)

    // navigation
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUiKtx)

    // Di
    implementation(Di.dagger)
    kapt(Di.daggerCompiler)

    // region Network
    implementation(Network.retrofit2)
    implementation(Network.retrofit2ConverterGson)
    implementation(Network.okhttp3LoggingInterceptor)
    implementation(Network.gson)
    implementation(Network.coil)

    // region Test
    testImplementation(Test.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Compose.version}") // TODO: move to Dependencies
    debugImplementation("androidx.compose.ui:ui-tooling:${Compose.version}") // TODO: move to Dependencies
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
