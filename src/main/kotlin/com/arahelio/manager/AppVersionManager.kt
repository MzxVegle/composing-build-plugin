package com.arahelio.manager

import com.android.build.api.dsl.ApplicationExtension
import com.arahelio.Config
import com.arahelio.manager.AppVersionManager.VersionUtil.autoVersionCode
import com.arahelio.manager.AppVersionManager.VersionUtil.autoVersionName

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

class AppVersionManager : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            if (!(project.name != "app" && !Config.isDebugMode)){
                extensions.configure(ApplicationExtension::class.java) {
                    logger.error("ApplicationExtension version--->")
                    with(it) {
                        defaultConfig {
                            if (target.name == "app"){
                                versionCode = autoVersionCode(target)
                                versionName = autoVersionName(target)
                            }else{
                                versionCode = Config.versionCode
                                versionName = Config.versionName
                            }

                        }
                    }
                }
            }

        }
    }

    object VersionUtil {
        fun autoVersionCode(
            target: Project,
            versionConfigName: String = "version.properties"
        ): Int {
            val file = target.file("${target.rootDir.path}\\dependency\\$versionConfigName")

            if (file.canRead()) {
                val prop = Properties().apply {
                    load(file.reader())
                }
                var current_version_code = prop.getProperty("CURRENT_VERSION_CODE").toInt()
//                target.logger.error("$versionConfigName--->versioncode:$current_version_code")
                val runTasks = target.gradle.startParameter.taskNames
                if ("assembleRelease" in runTasks && target.name == "app") {
                    // 版本号自增之后再写入文件（此处是关键，版本号自增+1）
                    prop["CURRENT_VERSION_CODE"] = (++current_version_code).toString()
                    prop.store(file.writer(), null)
                }

                return current_version_code


            } else {
                throw GradleException("Could not find version.properties!")
            }
        }

        fun autoVersionName(
            target: Project,
            versionConfigName: String = "version.properties"
        ):String {
            val file = target.file("${target.rootDir.path}\\dependency\\$versionConfigName")

            if (file.canRead()) {
                val prop = Properties().apply {
                    load(file.reader())
                }
                val version_prefix = prop["VERSION_PREFIX"]
                val version_name = prop["CURRENT_VERSION_NAME"]
                val version_code = prop["CURRENT_VERSION_CODE"]
                if ("".equals(version_name)){
                    throw  GradleException("VersionName can not be null!!!!pls check")
                }
                val nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"))
                return "${version_prefix}-$version_name.$version_code-$nowTime"




            } else {
                throw GradleException("Could not find version.properties!")
            }
        }
    }
}