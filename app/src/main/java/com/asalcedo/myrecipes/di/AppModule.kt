package com.asalcedo.myrecipes.di

import com.asalcedo.myrecipes.BuildConfig.BASE_URL
import com.asalcedo.myrecipes.data.RecipeRepositoryImpl
import com.asalcedo.myrecipes.data.local.dao.RecipeDao
import com.asalcedo.myrecipes.data.network.RecipeService
import com.asalcedo.myrecipes.domain.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.di
 * Created by Alex Salcedo Silva on 23/2/24 at 19:26
 * All rights reserve 2022.
 ***/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(service: RecipeService, dao: RecipeDao): RecipeRepository {
        return RecipeRepositoryImpl(service, dao)
    }

}