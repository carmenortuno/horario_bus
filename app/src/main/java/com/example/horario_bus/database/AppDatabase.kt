package com.example.horario_bus.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.horario_bus.database.horario.Horario
import com.example.horario_bus.database.horario.HorarioDao

@Database(entities = [Horario::class], version=1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun horarioDao(): HorarioDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "horario_bus_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}