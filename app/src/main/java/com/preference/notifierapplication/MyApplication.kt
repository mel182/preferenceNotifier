package com.preference.notifierapplication

import android.app.Application
import com.preference.preferencenotifier.notifier.PreferenceNotifier

class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        PreferenceNotifier
            .notifyOnConfigurationChanged(true)
                .init(applicationContext)
    }
}