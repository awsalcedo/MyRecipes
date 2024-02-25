package com.asalcedo.myrecipes.di

import android.content.Context
import androidx.room.Room
import com.asalcedo.myrecipes.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.di
 * Created by Alex Salcedo Silva on 25/2/24 at 14:24
 * All rights reserve 2022.
 ***/
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val RECIPE_DATABASE_NAME = "recipe_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecipeDatabase::class.java, RECIPE_DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipeDatabase) = db.getRecipeDao()
}