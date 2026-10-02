import java.util.Properties
import java.io.FileInputStream
plugins {
    id("com.android.application")
}

android {
    namespace = "com.priti.dailykit"
    compileSdk = 36

    val keystoreProperties = Properties()
    val keystorePropertiesFile = rootProject.file("keystore.properties")
    if (keystorePropertiesFile.exists()) {
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("dailykit-upload.jks")
            storePassword = keystoreProperties["DAILYKIT_STORE_PASSWORD"] as String?
            keyAlias = "dailykit"
            keyPassword = keystoreProperties["DAILYKIT_KEY_PASSWORD"] as String?
        }
    }

    defaultConfig {
        applicationId = "com.priti.dailykit"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("com.google.android.gms:play-services-ads:23.6.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
}
