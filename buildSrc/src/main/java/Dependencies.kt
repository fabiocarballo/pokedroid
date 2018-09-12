object Versions {
    const val kotlin = "1.2.60"
    const val rxJava2 = "2.2.1"
    const val options = "0.10"
    const val junit4 = "4.12"
    const val junit5AndroidPlugin = "1.0.32"
    const val junit5 = "5.1.0"
    const val mockitoKotlin = "2.0.0-RC1"
    const val lifecycleVersion = "2.0.0-rc01"
    const val junitPlatform = "1.2.0"
    const val junitJupiter = "5.2.0"
    const val junitVintage = "5.2.0"
    const val architectureComponents = "1.1.1"
}

object Dependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    const val options = "com.github.peter-tackage.kotlin-options:kotlin-options:${Versions.options}"
    const val rxJavaOptions = "com.github.peter-tackage.kotlin-options:kotlin-options-rxjava2-extensions:${Versions.options}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"

    // Testing Dependencies
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val architectureComponentsTesting = "android.arch.core:core-testing:${Versions.architectureComponents}"
}

object Modules {
    const val domain = ":domain"
    const val presentation = ":presentation"
}