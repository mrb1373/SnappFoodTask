plugins {
    alias(libs.plugins.snapp.android.library)
}

android {
    namespace = "com.snapp.testing"
}

dependencies {
    implementation(projects.core.network)
    testImplementation(libs.junit)
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}