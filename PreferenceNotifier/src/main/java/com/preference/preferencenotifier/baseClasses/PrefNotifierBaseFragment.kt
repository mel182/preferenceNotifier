package com.preference.preferencenotifier.baseClasses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.preference.preferencenotifier.interfaces.PreferenceStateListener
import com.preference.preferencenotifier.notifier.PreferenceNotifier
import com.preference.preferencenotifier.viewmodels.NotifierViewModel

/**
 * This is the base fragment class that containing [PreferenceNotifier] properties
 * @since 1.0
 * @author Melchior Vrolijk
 */
abstract class PrefNotifierBaseFragment : Fragment(), PreferenceStateListener
{
    var notifier: PreferenceNotifier = PreferenceNotifier

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribe()
    }

    /**
     * Subscribe to notifier view model for data updating
     * @since 1.0
     * @author Melchior Vrolijk
     */
    fun subscribe()
    {
        ViewModelProviders.of(this).get(NotifierViewModel::class.java)
                .onUpdatePreferenceValue().observe(this,{ preference ->
                    onPreferenceChanged(preference.key,preference.value)
                })
    }
}