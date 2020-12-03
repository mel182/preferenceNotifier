package com.preference.preferencenotifier.models

/**
 * The preference value data class which is the object that will be used to pass preference
 * value updates within the entire app.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
data class PreferenceValue(val key:String, val value:Any?)
