package com.example.nutritionanalysis.di

import javax.inject.Qualifier


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class MainDispatcher

/**
 * This qualifier is used to differentiate between the normal [android.content.SharedPreferences]
 * and the [EncryptedSharedPreferences].
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class Encrypted
