package com.arahelio

import org.gradle.api.artifacts.dsl.DependencyHandler

object Libraries {
    object Versions{
        val coreKtx = "1.12.0"
        val lifecycle = "2.7.0"
        val activityCompose = "1.8.0"
        val composeBom = "2023.08.00"
        val hilt = "2.50"
        val kongzueDialogX = "0.0.49"
        val multidex = "2.0.1"
        val appCompat = "1.6.1"
        val okhttp = "4.12.0"
        val retrofit = "2.9.0"
        val moshi = "1.15.0"
        val moshiConverter = "2.9.0"
        val toasty = "1.5.2"
        val eventBus = "3.3.1"
        val dataStore = "1.0.0"
        val utilcodex = "1.31.0"
        val startup = "1.1.1"
        val room = "2.6.1"
        val fastBle = "2.4.0"
        val permissionX = "1.7.1"
        val bleLibrary = "3.2.0"
        val barCodeScanner = "17.2.0"
        val cameraLib = "1.3.1"
        val zBar = "1.3.8"
        val bleCore = "1.9.9"
        val router = "1.3.0"
        val paging = "3.1.1"
        val jetpack_ble = "1.0.0-alpha02"
        val bracer = "1.0.7"
        val constraintLayoutCompose = "1.0.1"
        val constraintLayout = "2.1.4"
    }
    val  constraintLayoutCompose= "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    val coreKtx =  "androidx.core:core-ktx:${Versions.coreKtx}"
    val lifecycleRuntimeKtx =  "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycleViewModelCompose =  "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    val lifecycleRuntimeCompose =  "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val activityCompose =  "androidx.activity:activity-compose:${Versions.activityCompose}"
    val hilt =  "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler =  "com.google.dagger:hilt-compiler:${Versions.hilt}"
    val composeBom =  "androidx.compose:compose-bom:${Versions.composeBom}"
    val composeUi = "androidx.compose.ui:ui"
    val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    val composePreview = "androidx.compose.ui:ui-tooling-preview"
    val composeTooling = "androidx.compose.ui:ui-tooling"
    val composeUiAndroid = "androidx.compose.ui:ui-android"
    val composeMaterial3 =  "androidx.compose.material3:material3"
    val composeMaterial =  "androidx.compose.material:material"
    val paging_runtime = "androidx.paging:paging-runtime:${Versions.paging}"
    val paging_compose = "androidx.paging:paging-compose:1.0.0-alpha15"
    val startup = "androidx.startup:startup-runtime:${Versions.startup}"
    val roomRuntime =  "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler =  "androidx.room:room-compiler:${Versions.room}"
    val routerCompiler = "com.github.ssseasonnn.Butterfly:compiler:${Versions.router}"
    val router = "com.github.ssseasonnn.Butterfly:butterfly:${Versions.router}"
    val routerCompose = "com.github.ssseasonnn.Butterfly:butterfly-compose:${Versions.router}"
    val cameraLib = listOf(
        "androidx.camera:camera-camera2:${Versions.cameraLib}",
        "androidx.camera:camera-core:${Versions.cameraLib}",
        "androidx.camera:camera-lifecycle:${Versions.cameraLib}",
        "androidx.camera:camera-view:${Versions.cameraLib}",

    )

    val otherLibs = listOf(
        "com.squareup.okhttp3:okhttp:${Versions.okhttp}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}",
        "com.github.kongzue.DialogX:DialogX:${Versions.kongzueDialogX}",
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.moshi:moshi-kotlin:${Versions.moshi}",
        "com.squareup.moshi:moshi:${Versions.moshi}",
        "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}",
        "com.github.GrenderG:Toasty:${Versions.toasty}",
        "org.greenrobot:eventbus:${Versions.eventBus}",
        "androidx.datastore:datastore-preferences:${Versions.dataStore}",
        "com.blankj:utilcodex:${Versions.utilcodex}",
        "com.guolindev.permissionx:permissionx:${Versions.permissionX}",
        "com.github.Jasonchenlijian:FastBle:${Versions.fastBle}",
        "cn.com.superLei:blelibrary:${Versions.bleLibrary}",
        "com.google.mlkit:barcode-scanning:${Versions.barCodeScanner}",
        "com.github.bingoogolapple.BGAQRCode-Android:zbar:${Versions.zBar}",
        "com.github.buhuiming:BleCore:${Versions.bleCore}",
        paging_runtime,
        paging_compose,
        "androidx.bluetooth:bluetooth:${Versions.jetpack_ble}",
        "com.github.ssseasonnn:Bracer:${Versions.bracer}"

        )
    val kspLibs = listOf(
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}")
    //本地依赖
    val localProject = listOf(
        ":moduleBase:common",
        ":moduleBase:basic",
        ":moduleBase:repository",
        ":moduleBase:icon",
//        ":moduleBase:database"
    )
    val coreProject = listOf(
//        ":moduleCore:splash",
        ":moduleCore:users",
        ":moduleCore:main"
    )

    fun DependencyHandler.kotlinProject() {
        add("implementation", coreKtx)
    }


    fun DependencyHandler.androidProject() {
        add("implementation", lifecycleRuntimeKtx)
        add("implementation", lifecycleRuntimeCompose)
        add("implementation", lifecycleViewModelCompose)

        add("implementation", constraintLayoutCompose)

        add("implementation", multidex)
        add("implementation", appCompat)
        add("implementation", startup)
        add("implementation", roomRuntime)
        add("implementation", roomKtx)

    }
    fun DependencyHandler.butterflyRouter(){
        add("implementation", router)
        add("implementation", routerCompose)
        add("ksp", routerCompiler)
    }



    fun DependencyHandler.composeProject() {
        add("implementation", activityCompose)

        add("implementation", platform(composeBom))
        add("implementation", composeMaterial3)
        add("implementation", composeMaterial)

        add("implementation", composeUi)
        add("implementation", composeUiGraphics)
        add("implementation", composePreview)
        add("implementation", composeUiAndroid)
        add("implementation", composeTooling)


    }
    fun DependencyHandler.hiltProject(){
        add("implementation", hilt)
        add("kapt", hiltCompiler)
    }
    fun DependencyHandler.otherProject(){
        otherLibs.map {
            add("implementation",it)
        }
        cameraLib.map {
            add("implementation",it)
        }
        kspLibs.map {
            add("ksp",it)
        }


        add("kapt", roomCompiler)
    }




    fun DependencyHandler.coreProject(){

        coreProject.map{
            add("implementation",project(mapOf(Pair("path",it))))

        }
    }
}