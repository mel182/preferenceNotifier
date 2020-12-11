package com.preference.notifierapplication

import android.app.Application
import com.preference.preferencenotifier.notifier.PreferenceNotifier

class PreferenceNotifier : Application()
{
    override fun onCreate() {
        super.onCreate()
        PreferenceNotifier
                .init(applicationContext)
    }
}