package com.planner.di

import android.content.Context
import androidx.room.Room
import com.planner.room.DayPlansDao
import com.planner.room.DayPlansDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDrinkCategoriesDatabase(@ApplicationContext context: Context): DayPlansDatabase {
        return Room.databaseBuilder(
            context,
            DayPlansDatabase::class.java,
            DayPlansDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideDrinkCategoriesDao(dayPlansDatabase: DayPlansDatabase): DayPlansDao {
        return dayPlansDatabase.dayPlansDao()
    }


}