<h1 align="center">Github API Using Jetpack Compose</h1>

## Introduction 🙋‍♂️

### view pull request [develop -> master](https://github.com/Aks-4125/github-api-compose/pull/1)
### view Speer test on [develop/speer](https://github.com/Aks-4125/github-api-compose/tree/dev/speer-test) branch

Github open API using Android Kotin Jetpack Compose


The Android project is built using Kotlin and follows the MVVM (Model-View-ViewModel) architectural pattern. It utilizes the latest AndroidX libraries, Jetpack components, and popular third-party libraries for different functionalities.

## Features

The project includes the following features and components:

- Integration of AndroidX Core, Lifecycle, and Activity Compose libraries for UI development and lifecycle management.
- Usage of Compose UI library for building modern and reactive user interfaces.
- Implementation of Hilt for dependency injection, facilitating easy and efficient dependency management.
- Networking capabilities using Retrofit and OkHttp for making API requests and handling network operations.
- Data parsing and serialization using Moshi JSON library.
- Image loading and caching with Coil library.
- Navigation between different screens using the Navigation Compose library.
- Memory leak detection with LeakCanary library.
- Implementation of a splash screen using the Core Splash Screen library.
- Asynchronous programming using Kotlin Coroutines for efficient and non-blocking operations.
- Unit testing and mocking with JUnit, MockK, and Kotlin Coroutines Test libraries.

# Android Project Libraries

This repository contains an Android project that utilizes the following libraries:

## AndroidX Libraries

- [androidx.core:core-ktx:1.10.1](https://developer.android.com/jetpack/androidx/releases/core)
- [androidx.lifecycle:lifecycle-runtime-ktx:2.6.1](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [androidx.activity:activity-compose:1.7.2](https://developer.android.com/jetpack/androidx/releases/activity)
- [androidx.compose.ui:ui](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- [androidx.compose.ui:ui-graphics](https://developer.android.com/jetpack/androidx/releases/compose-ui-graphics)
- [androidx.compose.ui:ui-tooling-preview](https://developer.android.com/jetpack/androidx/releases/compose-ui-tooling-preview)
- [androidx.compose.material3:material3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

## Testing Libraries

- [junit:junit:4.13.2](https://junit.org/junit4/)
- [org.junit.jupiter:junit-jupiter-api:5.8.2](https://junit.org/junit5/)
- [androidx.test.ext:junit:1.1.5](https://developer.android.com/jetpack/androidx/releases/test)
- [androidx.test.espresso:espresso-core:3.5.1](https://developer.android.com/training/testing/espresso)
- [androidx.compose.ui:ui-test-junit4](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- [androidx.compose.ui:ui-tooling](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- [androidx.compose.ui:ui-test-manifest](https://developer.android.com/jetpack/androidx/releases/compose-ui)

## Dependency Injection

- [com.google.dagger:hilt-android:2.46.1](https://dagger.dev/hilt/)
- [com.google.dagger:hilt-compiler:2.46.1](https://dagger.dev/hilt/)

## Networking

- [com.squareup.retrofit2:retrofit:2.9.0](https://square.github.io/retrofit/)
- [com.squareup.retrofit2:converter-moshi:2.9.0](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi)
- [com.squareup.okhttp3:okhttp:5.0.0-alpha.11](https://square.github.io/okhttp/)
- [com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11](https://square.github.io/okhttp/)

## Deserializer

- [com.squareup.moshi:moshi-kotlin-codegen:1.15.0](https://github.com/square/moshi)
- [com.squareup.moshi:moshi:1.15.0](https://github.com/square/moshi)
- [com.squareup.moshi:moshi-kotlin:1.15.0](https://github.com/square/moshi)
- [com.squareup.moshi:moshi-adapters:1.15.0](https://github.com/square/moshi)

## Image Loading

- [io.coil-kt:coil-compose:2.4.0](https://github.com/coil-kt/coil)

## Navigation

- [androidx.navigation:navigation-compose:2.6.0](https://developer.android.com/jetpack/androidx/releases/navigation)

## Memory Leak Detection

- [com.squareup.leakcanary:leakcanary-android:2.11](https://github.com/square/leakcanary)

## Splash Screen

- [androidx.core:core-splashscreen:1.0.1](https://developer.android.com/jetpack/androidx/releases/core-splashscreen)

## Coroutine

- [org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1](https://github.com/Kotlin/kotlinx.coroutines)

## Testing UI + Unit

- [io.mockk:mockk:1.13.4](https://mockk.io/)
- [android.arch.core:core-testing:1.1.1](https://developer.android.com/topic/libraries/architecture/testing)
- [com.google.dagger:hilt-android-testing:2.44](https://dagger.dev/hilt/testing)
- [com.google.dagger:hilt-android-compiler:2.44.2](https://dagger.dev/hilt/testing)
- [org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1](https://github.com/Kotlin/kotlinx.coroutines)

Please refer to the individual library documentation for usage and more information.

