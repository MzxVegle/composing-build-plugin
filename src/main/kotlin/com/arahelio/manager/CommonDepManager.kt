package com.arahelio.manager

import com.arahelio.Libraries.androidProject
import com.arahelio.Libraries.hiltProject
import com.arahelio.Libraries.kotlinProject
import com.arahelio.Libraries.otherProject
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonDepManager : Plugin<Project> {

    override fun apply(target: Project) {
        with(target.dependencies) {
//            this.constraints.
            kotlinProject()
            androidProject()
            hiltProject()
            otherProject()

        }





    }
}


