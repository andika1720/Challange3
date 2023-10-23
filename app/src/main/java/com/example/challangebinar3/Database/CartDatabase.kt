package com.example.challangebinar3.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cart::class], version = 2)
abstract class CartDatabase: RoomDatabase() {

    abstract val cartDao: CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null


        fun getInstance(context: Context): CartDatabase {

            if (INSTANCE == null) {
                synchronized(CartDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CartDatabase::class.java, "cart_database"
                    )
                        .fallbackToDestructiveMigration()
                        //.addMigrations(Migration1to2())
                        .build()
                }
            }
                return INSTANCE as CartDatabase
            }

    }
}

