plugins {
    alias(libs.plugins.snapp.android.library)
    alias(libs.plugins.snapp.hilt)
}

android {
    namespace = "com.snapp.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.gson.converter)

    testImplementation(libs.junit)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
}