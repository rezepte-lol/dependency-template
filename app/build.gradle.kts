import example.app

plugins {
    id("com.android.application")
    kotlin("android")

    id("example.dependencies")
}

android {
    compileSdk = app.CompileSdkVersion

    defaultConfig {
        applicationId = "example.app"
        minSdk = app.MinimumSdkVersion
        targetSdk = app.TargetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    app {
        android {
            coreLibraryDesugaring(desugaring)

            implementation(legacySupport)
            implementation(appcompat)
            implementation(coreKtx)

            implementation(composeActivity)
        }
        compose {
            implementation(runtime)
            implementation(foundation)
            implementation(material)
            implementation(uiTooling)
        }
        composeTest {
            androidTestImplementation(ui)
            androidTestImplementation(junit4)
        }
        androidTest {
            testImplementation(archCore)
            androidTestImplementation(core)
            androidTestImplementation(junitExt)
            androidTestImplementation(runner)
            androidTestUtil(orchestrator)
            androidTestImplementation(espresso)
        }
    }
}
