package com.example.test_application.domain.provider

import android.content.Context
import androidx.room.Room
import com.example.test_application.domain.DATABASE_NAME
import com.example.test_application.domain.GeneralDatabase
import com.example.test_application.domain.getMigrations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseProviderImpl @Inject constructor() : DatabaseProvider {

    override fun provideDatabase(context: Context): GeneralDatabase =
        Room.databaseBuilder(context, GeneralDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .addMigrations(*getMigrations())
            .build()
}
