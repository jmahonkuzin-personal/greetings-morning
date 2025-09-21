import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleDevtoolsKsp) // ksp for room annotation processing
    alias(libs.plugins.androidXRoom)
//    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20-RC2"
//    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            freeCompilerArgs += listOf("-Xbinary=bundleId=com.example.hopouttabed")
        }
    }

//    sourceSets.commonMain {
//
//    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("com.jakewharton.timber:timber:5.0.1")
            implementation(libs.androidx.compose.foundation)

            // Room (Database)

            implementation(libs.material3)

            // Hilt (Dependency Injection)
//            implementation(libs.hilt.android)
//            implementation(libs.androidx.hilt.navigation.compose)
//            ksp(libs.hilt.compiler)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
//            implementation(compose.navigation)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.datetime)

            implementation(libs.androidx.room.runtime)
            // The SQLite Driver interfaces
            implementation(libs.androidx.sqlite)
            // The bundled SQLite driver implementation
            implementation(libs.androidx.sqlite.bundled)
//            implementation()

            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta05")
        }
        iosMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.hopouttabed"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    dependencies {
        ksp(libs.androidx.room.compiler) // Assuming libs.room.compiler points to the correct Room compiler artifact
    }

    defaultConfig {
        applicationId = "com.example.hopouttabed"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures { compose = true }
}

room {
    // Room will export schema files here
    schemaDirectory("$projectDir/schemas")
}

compose.resources {
    // pick ONE and stick with it
    packageOfResClass = "com.example.hopouttabed.composeapp.generated.resources"
    publicResClass = true
}

dependencies {
    debugImplementation(compose.uiTooling)

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)   // ← add this
    add("kspCommonMainMetadata", libs.androidx.room.compiler)  // ← add this
//    add("kspAndroid", libs.hilt.compiler)
}


//configurations.forEach { println(it.name) }
