object Versions {
    val kotlin = "1.2.60"
    val rxJava2 = "2.2.1"
    val options = "0.10"
    val junit4 = "4.12"
    val junit5 = "5.1.0"
    val mockitoKotlin = "2.0.0-RC1"
}

object Dependencies {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    val options = "com.github.peter-tackage.kotlin-options:kotlin-options:${Versions.options}"
    val rxJavaOptions = "com.github.peter-tackage.kotlin-options:kotlin-options-rxjava2-extensions:${Versions.options}"

    // Testing Dependencies
    val junit4 = "junit:junit:${Versions.junit4}"
    val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
}