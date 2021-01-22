package com.planner.di

import com.planner.repository.PlansRepository
import com.planner.repository.PlansRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePlansRepository(): PlansRepository{
        return PlansRepositoryImpl()
    }
}