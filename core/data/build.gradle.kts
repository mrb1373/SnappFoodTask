plugins {
    alias(libs.plugins.snapp.android.library)
    alias(libs.plugins.snapp.hilt)
}

android {
    namespace = "com.snapp.data"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.network)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(projects.core.testing)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
}