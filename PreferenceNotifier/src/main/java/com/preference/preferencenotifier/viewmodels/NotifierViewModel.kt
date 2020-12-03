package com.preference.preferencenotifier.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.preference.preferencenotifier.notifier.PreferenceNotifier
import com.preference.preferencenotifier.models.PreferenceValue

/**
 * The notifier view model
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class NotifierViewModel: ViewModel() {
    fun onUpdatePreferenceValue():LiveData<PreferenceValue> = PreferenceNotifier.updatedPreferenceValue
}