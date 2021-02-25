A Sound Recorder App
======================

**A simple sound recorder app that uses the MediaRecorder API to record audio sounds.
These files are uploaded to Cloud Firestore and displayed in a Recycler view.

# Development Environment

The app is written entirely in Kotlin and uses the Gradle build system.
Add your own google-services.json from Firebase file before building

# Architecture
The architecture is built around
[How to implement a Clean Architecture on Android](https://proandroiddev.com/how-to-implement-a-clean-architecture-on-android-2e5e8c8e81fe).

# Jetpack Libraries

- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Navigation component](https://developer.android.com/guide/navigation)
- [Firebase Cloud Functions](https://firebase.google.com/docs/functions/)
- [Android Ktx extensions](https://developer.android.com/kotlin/ktx)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [coroutines](https://developer.android.com/kotlin/coroutines)

## Firebase
-  [Cloud Firestore](https://firebase.google.com/docs/firestore/) is my database.
- [Firebase Cloud Functions](https://firebase.google.com/docs/functions/)
allowed me to run backend code.






