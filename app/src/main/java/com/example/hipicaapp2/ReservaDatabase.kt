package com.example.hipicaapp

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context

@Database(entities = [Reserva::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ReservaDatabase : RoomDatabase() {

    abstract fun reservaDao(): ReservaDao

    companion object {
        @Volatile
        private var INSTANCE: ReservaDatabase? = null

        fun getDatabase(context: Context): ReservaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReservaDatabase::class.java,
                    "reserva_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
