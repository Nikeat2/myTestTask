package com.example.trainingtesttask.di

import android.app.Application

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(context = this).build()
    }

}