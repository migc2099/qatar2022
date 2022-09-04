package com.migc.qatar2022.di

import android.content.Context
import androidx.room.Room
import com.migc.qatar2022.common.Constants.QATAR_DATABASE
import com.migc.qatar2022.data.QatarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): QatarDatabase {
        return Room.databaseBuilder(
            context,
            QatarDatabase::class.java,
            QATAR_DATABASE
        ).build()
    }

}