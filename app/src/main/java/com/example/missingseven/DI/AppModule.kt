package com.example.missingseven.DI

import android.content.Context
import android.content.SharedPreferences
import com.example.missingseven.Database.DataInitializer
import com.example.missingseven.Database.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Hilt Dependency Inject
 * module object for app level dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("missing_seven", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePrefManager(preferences: SharedPreferences) = PrefManager(preferences)

    @Singleton
    @Provides
    fun provideDataInitializer(@ApplicationContext context: Context): DataInitializer {
        return DataInitializer(context)
    }

}