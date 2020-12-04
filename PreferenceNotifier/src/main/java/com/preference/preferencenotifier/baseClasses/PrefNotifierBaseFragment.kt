package com.preference.preferencenotifier.baseClasses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.preference.preferencenotifier.interfaces.PreferenceStateListener
import com.preference.preferencenotifier.notifier.PreferenceNotifier
import com.preference.preferencenotifier.viewmodels.NotifierViewModel

abstract class PrefNotifierBaseFragment : Fragment(), PreferenceStateListener
{
    var notifier: PreferenceNotifier = PreferenceNotifier

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribe()
    }

    fun subscribe()
    {
        ViewModelProviders.of(this).get(NotifierViewModel::class.java)
                .onUpdatePreferenceValue().observe(this,{ preference ->
                    onPreferenceChanged(preference.key,preference.value)
                })
    }
}