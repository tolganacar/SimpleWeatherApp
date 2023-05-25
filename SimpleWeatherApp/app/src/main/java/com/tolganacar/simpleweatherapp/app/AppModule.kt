package com.tolganacar.simpleweatherapp.app

import android.app.Application
import com.tolganacar.simpleweatherapp.util.ResourceProvider
import com.tolganacar.simpleweatherapp.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    @Singleton
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProviderImpl(application.applicationContext)
    }
}