package com.example.test_application.di.domain

import android.content.Context
import com.example.test_application.domain.GeneralDatabase
import com.example.test_application.domain.dao.TaskDao
import com.example.test_application.domain.provider.DatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideTaskDao(database: GeneralDatabase): TaskDao = database.getTaskDao()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        databaseProvider: DatabaseProvider,
    ): GeneralDatabase = databaseProvider.provideDatabase(context)
}
