plugins {
    alias(libs.plugins.snapp.android.feature)
    alias(libs.plugins.snapp.android.library.compose)
}

android {
    namespace = "com.snapp.feature.home"
}

dependencies {

    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.network)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}