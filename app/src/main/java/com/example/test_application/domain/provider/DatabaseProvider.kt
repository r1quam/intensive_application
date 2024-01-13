package com.example.test_application.domain.provider

import android.content.Context
import com.example.test_application.domain.GeneralDatabase

interface DatabaseProvider {

    fun provideDatabase(context: Context): GeneralDatabase
}
