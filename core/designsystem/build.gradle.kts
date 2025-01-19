plugins {
    alias(libs.plugins.snapp.android.library)
    alias(libs.plugins.snapp.android.library.compose)
}

android {
    namespace = "com.snapp.designsystem"
}

dependencies {
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.androidx.material.iconsExtended)
    api(libs.androidx.material3.navigationSuite)
}