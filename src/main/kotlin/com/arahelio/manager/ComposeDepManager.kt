package com.arahelio.manager

import com.android.build.api.dsl.CommonExtension
import com.arahelio.Libraries.composeProject
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeDepManager : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure(CommonExtension::class.java) {


                with(it) {
                    buildFeatures {
                        compose = true
                    }
                    composeOptions {
                        kotlinCompilerExtensionVersion = "1.5.1"
                    }
                }

            }
            with(dependencies) {
                composeProject()

            }
        }

    }
}


