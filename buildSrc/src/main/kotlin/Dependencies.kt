// AndroidStudio won't highlight outdate library after switch to use Kotlin DSL
// https://stackoverflow.com/questions/62729468/androidstudio-wont-highlight-outdate-library-after-switch-to-use-kotlin-dsl

object Modules {
    const val core = ":core"
}

object AndroidX {
    private const val version = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
}

object Compose {
    const val version = "1.0.1"
}

object Di {
    private const val version = "2.40.5"
    const val dagger = "com.google.dagger:dagger:$version"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
}

object Network {
    const val retrofit2 = "com.squareup.retrofit2:retrofit:2.8.0"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    const val gson = "com.google.code.gson:gson:2.8.9"
}
