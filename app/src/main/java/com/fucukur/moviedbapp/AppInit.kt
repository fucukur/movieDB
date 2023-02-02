package com.fucukur.moviedbapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppInit : Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}
