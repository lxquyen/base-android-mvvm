package com.lxquyen.sample.common

import android.app.Application
import com.lxquyen.sample.BuildConfig
import com.lxquyen.sample.common.di.component.AppComponent
import com.lxquyen.sample.common.di.component.DaggerAppComponent
import com.lxquyen.sample.common.di.module.CommonModule
import com.lxquyen.sample.common.di.module.RemoteSourceModule
import timber.log.Timber

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .remoteSourceModule(RemoteSourceModule("http://5cef377a1c2baf00142cc649.mockapi.io/api/"))
            .commonModule(CommonModule(this))
            .build()
    }
}