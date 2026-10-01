import java.util.Base64

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    `maven-publish`
    signing
}

android {
    namespace = "org.connecttag.lib.kotlin.about"
    compileSdk = 37

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES"
        }
        jniLibs {
            useLegacyPackaging = false
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = project.property("LIB_GROUP").toString()
                artifactId = project.property("LIB_ARTIFACT_ID").toString()
                version = project.property("LIB_VERSION").toString()

                pom {
                    name.set("ConnectTag Lib About")
                    description.set("About App and About Developer UI screens and components for ConnectTag Kotlin projects")
                    url.set("https://github.com/connecttag/connecttaglibabout")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("connecttagye")
                            name.set("Connect Tag")
                            email.set("connecttagye@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/connecttag/connecttaglibabout.git")
                        developerConnection.set("scm:git:ssh://git@github.com:connecttag/connecttaglibabout.git")
                        url.set("https://github.com/connecttag/connecttaglibabout/tree/main")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "Local"
                url = uri(layout.buildDirectory.dir("repo"))
            }
            maven {
                name = "OSSRH"
                url = uri("https://central.sonatype.com/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("OSSRH_USERNAME") ?: project.findProperty("ossrhUsername")?.toString()
                    password = System.getenv("OSSRH_TOKEN") ?: project.findProperty("ossrhPassword")?.toString()
                }
            }
        }
    }

    signing {
        val rawSigningKey = System.getenv("GPG_PRIVATE_KEY") ?: project.findProperty("signingKey")?.toString()
        val signingPassword = System.getenv("GPG_PASSPHRASE") ?: project.findProperty("signingPassword")?.toString()
        val signingKeyId = System.getenv("GPG_KEY_ID") ?: project.findProperty("signingKeyId")?.toString()

        if (rawSigningKey != null && signingPassword != null) {
            val signingKey = if (rawSigningKey.contains("BEGIN PGP")) {
                rawSigningKey
            } else {
                try {
                    String(Base64.getDecoder().decode(rawSigningKey.trim()), Charsets.UTF_8)
                } catch (e: Exception) {
                    rawSigningKey
                }
            }

            if (signingKeyId != null) {
                useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
            } else {
                useInMemoryPgpKeys(signingKey, signingPassword)
            }
            sign(publishing.publications["release"])
        } else if (project.hasProperty("signing.keyId")) {
            sign(publishing.publications["release"])
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.coil.compose)
    implementation(libs.kotlinx.serialization.json)
}
