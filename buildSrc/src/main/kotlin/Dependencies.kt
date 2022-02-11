// AndroidStudio won't highlight outdate library after switch to use Kotlin DSL
// https://stackoverflow.com/questions/62729468/androidstudio-wont-highlight-outdate-library-after-switch-to-use-kotlin-dsl

object Modules {
    const val core = ":core"
}

object Android {
    private const val version = "1.4.0"
    const val material = "com.google.android.material:material:$version"
    const val appcompat = "androidx.appcompat:appcompat:$version"
}

object AndroidX {
    private const val version = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
}

object Compose {
    const val version = "1.1.0"
    const val core = "androidx.core:core-ktx:1.7.0"
    const val ui = "androidx.compose.ui:ui:$version"
    const val material = "androidx.compose.material:material:$version"
    const val tooling = "androidx.compose.ui:ui-tooling:$version"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
    const val activity = "androidx.activity:activity-compose:1.4.0"
}

object Di {
    private const val version = "2.40.5"
    const val dagger = "com.google.dagger:dagger:$version"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
}

object Network {
    const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    const val gson = "com.google.code.gson:gson:2.8.9"

    const val coil = "io.coil-kt:coil-compose:1.4.0"
}

object Test {
    const val junit = "junit:junit:4.13.2"
}
