buildscript {
    dependencies {
        classpath(
            "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1"
        )
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}
plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
