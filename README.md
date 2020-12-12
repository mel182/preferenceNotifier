# Preference Notifier
This is an Android libary which enabled all UI components to be notified when a shared preference value is updated and added. Although Android provided a native Android [PreferenceActivity](https://developer.android.com/reference/android/preference/PreferenceActivity), but it was deprecated in API level 29.
The preference notifier is an easy to use library which can easily be migrated from your existent shared preference.

## Dependencies

**build.gradle (project):**
```bash
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**build.gradle (module):**
```bash
dependencies {
    ...
    implementation 'com.github.mel182:preferenceNotifier:Tag'
}
```

Make sure your support atleast Android API level 16.

## Usage

```kotlin
class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        PreferenceNotifier
             .init(applicationContext)
    }
}
```

To migrate from an existing shared preference:

```kotlin
class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

       val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

        PreferenceNotifier
                .migrate(sharedPreference)
                .init(applicationContext)
    }
}
```

By default, it does not notify when the app configuration changes (e.g. on screen orientation changes). In order to enable this feature:

```kotlin
class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        PreferenceNotifier
            .notifyOnConfigurationChanged(true)
            .init(applicationContext)
    }
}
```

The library contains a base Activity and fragment which makes the migration process even easier.

For Activities:

```kotlin
class MainActivity : PrefNotifierBase()
{
    //.......................

    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        // Handle action when preference key changes
    }

}
```
For Fragments:

```kotlin
class MyFragment : PrefNotifierBaseFragment()
{
    //.......................
    
    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        // Handle action when preference key changes
    }
}
```

The onPreferenceChanged function is totally optional meaning you can extends the PrefNotifierBase or PrefNotifierBaseFragment without defining it.

To add or update a preference value use:

```kotlin
class MyFragment : PrefNotifierBaseFragment()
{
    //.......................
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notifier.setValue("key","pref value")
    }
    
    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        // Handle action when preference key changes
    }
}
```

```kotlin
class MainActivity : PrefNotifierBase()
{
    //.......................

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         notifier.setValue("key","pref value")
    }

    override fun onPreferenceChanged(key: String, value: Any?) {
        super.onPreferenceChanged(key, value)
        // Handle action when preference key changes
    }
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
