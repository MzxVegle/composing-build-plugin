package com.arahelio.manager

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.arahelio.Config
import org.gradle.api.Plugin
import org.gradle.api.Project

class ModulePluginManager : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            if (!Config.isDebugMode) {
                plugins.run {
                    apply("com.android.library")

                }
                extensions.configure(LibraryExtension::class.java){
                    with(it){
                        sourceSets {
                            with(this.getByName("main")) {
                                manifest.srcFile("src/main/AndroidManifest.xml")
                            }
                        }
                    }
                }
            } else {
                plugins.run {
                    apply("com.android.application")
                }
                extensions.configure(ApplicationExtension::class.java){
                    with(it){
                        sourceSets {
                            with(this.getByName("main")) {
                                manifest.srcFile("src/main/module/AndroidManifest.xml")
                                java.srcDir("src/main/module")
                            }
                        }
                    }
                }
            }

        }
    }
}