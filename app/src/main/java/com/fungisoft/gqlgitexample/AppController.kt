package com.fungisoft.gqlgitexample

import android.app.Application
import android.content.Context

class AppController : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: AppController

        /**
         * Never use this method for UI Context
         */
        val context: Context
            get() = instance
    }
}
