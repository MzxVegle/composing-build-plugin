package com.arahelio.manager

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.arahelio.Config
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.Exec
import org.jetbrains.kotlin.gradle.dsl.KaptExtensionConfig
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File


class BaseConfigManager : Plugin<Project> {
    override fun apply(target: Project) {
        val name = target.name
        with(target) {


            logger.error("start ConfigManager,current project:$name")
            plugins.run {
                apply("kotlin-android")
                apply("kotlin-kapt")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
                apply("kotlin-parcelize")
            }

            extensions.configure(KaptExtensionConfig::class.java){
                it.correctErrorTypes = true
                it.arguments {
                    arg("room.schemaLocation", "$projectDir/schemas")
                    arg("room.incremental", "true")

                }
            }

            extensions.configure(CommonExtension::class.java) {
                with(it) {
                    resourcePrefix = target.name
                    namespace = "com.project.${target.name}"
                    compileSdk = Config.compileSdk

                    defaultConfig {
                        manifestPlaceholders["app_name"] = Config.app_name


                        when {

                            plugins.hasPlugin("com.android.application") -> {
                                logger.error("$name project is application project")
                                with(this as ApplicationDefaultConfig) {
                                    applicationId = Config.applicationId
                                    targetSdk = Config.targetSdk
                                    multiDexEnabled = true

                                }
                            }

                            plugins.hasPlugin("com.android.library") -> {
                                if (name.equals("common")){
                                    buildFeatures {
                                        buildConfig = true
                                    }
                                    buildConfigField("Boolean","openLog","${Config.openLog}")

                                }
                                logger.error("$name project is library project")
                                testOptions {
                                    targetSdk = Config.targetSdk
                                }

                            }
                        }
                        minSdk = Config.minSdk
                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                        vectorDrawables {
                            useSupportLibrary = true
                        }
                    }

                    kotlinOptions {
                        jvmTarget = "17"
                    }
                    when {
                        plugins.hasPlugin("com.android.application") -> {
                            with(it as ApplicationExtension) {
                                buildTypes {
                                    release {
                                        isMinifyEnabled = false
                                        proguardFiles(
                                            getDefaultProguardFile("proguard-android-optimize.txt"),
                                            "proguard-rules.pro"
                                        )
                                    }
                                }
                            }
                        }

                        plugins.hasPlugin("com.android.library") -> {
                            with(it as LibraryExtension) {

                                buildTypes {
                                    release {
                                        isMinifyEnabled = false
                                        proguardFiles(
                                            getDefaultProguardFile("proguard-android-optimize.txt"),
                                            "proguard-rules.pro"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    tasks.register("listFiles",Exec::class.java){
                        val tree = fileTree(File("libs")){
                            it.include("*.jar")
                            it.include("*.aar")
                        }
                        logger.error("treeFiles--->${tree.files.joinToString()}")

                    }
                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }



                    packaging {
                        jniLibs {
                            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                        }

                    }

                }

            }


        }
    }

    fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware).extensions.configure("kotlinOptions", block)
    }
}