package com.arahelio.manager

import com.android.build.api.dsl.CommonExtension
import com.arahelio.Libraries.butterflyRouter
import org.gradle.api.Plugin
import org.gradle.api.Project

class ButterflyRouterManager:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            plugins.apply("io.github.ssseasonnn.butterfly")
            extensions.configure(CommonExtension::class.java) {
                with(it){
                    defaultConfig {
                        packaging {
                            resources.excludes.add("META-INF/*")
                        }
                    }
                }
            }

            with(dependencies){
                butterflyRouter()
            }

        }
    }
}