@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    //Javax Inject
    api(libs.javax.inject)

    // Kotlin
    api(libs.kotlinx.coroutines.core.v180)

    // Paging - Common
    api(libs.androidx.paging.common.ktx)
}