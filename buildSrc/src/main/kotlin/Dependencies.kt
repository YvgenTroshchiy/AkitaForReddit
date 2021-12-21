object Dependencies {

    const val composeVersion = "1.0.1"

    object Di {
        private const val daggerVersion = "2.40.5"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object Network {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
        const val gson = "com.google.code.gson:gson:2.8.9"
    }
}
