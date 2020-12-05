package com.preference.preferencenotifier.baseClasses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.preference.preferencenotifier.interfaces.PreferenceStateListener
import com.preference.preferencenotifier.notifier.PreferenceNotifier
import com.preference.preferencenotifier.viewmodels.NotifierViewModel

/**
 * This is the preference notifier base class which is a sub class of [AppCompatActivity].
 * By extending this class automatically subscribe to the [PreferenceNotifier]
 *
 * @author Melchior Vrolijk
 * @since version 1.0
 */
abstract class PrefNotifierBase : AppCompatActivity(), PreferenceStateListener
{
    var notifier: PreferenceNotifier = PreferenceNotifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe()
    }

    /**
     * Subscribe to the notifier internal view model
     */
    fun subscribe()
    {
        ViewModelProviders.of(this).get(NotifierViewModel::class.java)
            .onUpdatePreferenceValue().observe(this,{ preference ->
                onPreferenceChanged(preference.key,preference.value)
            })
    }
}