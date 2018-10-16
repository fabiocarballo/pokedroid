object Versions {
    const val kotlin = "1.2.60"
    const val rxJava2 = "2.2.1"
    const val options = "0.10"
    const val junit4 = "4.12"
    const val junit5AndroidPlugin = "1.0.32"
    const val junit5 = "5.1.0"
    const val mockitoKotlin = "2.0.0-RC1"
    const val junitPlatform = "1.2.0"
    const val junitJupiter = "5.2.0"
    const val junitVintage = "5.2.0"
    const val architectureComponents = "1.1.0"
    const val daggerVersion = "2.17"
    const val rxAndroid2 = "2.0.2"
    const val supportLibraries = "28.0.0-alpha1"
    const val supportTest = "1.0.2"
    const val espressoCore = "3.0.2"
    const val retrofit = "2.4.0"
}

object Dependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    const val options = "com.github.peter-tackage.kotlin-options:kotlin-options:${Versions.options}"
    const val rxJavaOptions = "com.github.peter-tackage.kotlin-options:kotlin-options-rxjava2-extensions:${Versions.options}"
    const val architectureComponentsExtensions = "android.arch.lifecycle:extensions:${Versions.architectureComponents}"
    const val architectureComponentsCompiler = "android.arch.lifecycle:compiler:${Versions.architectureComponents}"
    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid2}"
    const val supportCompat = "com.android.support:support-compat:${Versions.supportLibraries}"
    const val appCompat = "com.android.support:appcompat-v7:${Versions.supportLibraries}"
    const val recyclerview = "com.android.support:recyclerview-v7:${Versions.supportLibraries}"
    const val supportAnnotations = "com.android.support:support-annotations:${Versions.supportLibraries}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // Testing Dependencies
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val architectureComponentsTesting = "android.arch.core:core-testing:${Versions.architectureComponents}"
    const val supportTestRunner = "com.android.support.test:runner:${Versions.supportTest}"
    const val supportEspressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"
    const val supportTestRules = "com.android.support.test:rules:${Versions.supportTest}"
}

object Modules {
    const val domain = ":domain"
    const val presentation = ":presentation"
}