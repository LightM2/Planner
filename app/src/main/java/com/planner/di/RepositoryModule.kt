package com.planner.di

import com.planner.repository.PlansRepository
import com.planner.repository.PlansRepositoryImpl
import com.planner.room.DayPlansDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePlansRepository(
        dayPlansDao: DayPlansDao,
    ): PlansRepository {
        return PlansRepositoryImpl(
            dayPlansDao = dayPlansDao,
        )
    }
}