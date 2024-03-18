package com.asalcedo.myrecipes.di

import com.asalcedo.myrecipes.data.network.Dispatcher
import com.asalcedo.myrecipes.data.network.AppDispatchers.Default
import com.asalcedo.myrecipes.data.network.AppDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}