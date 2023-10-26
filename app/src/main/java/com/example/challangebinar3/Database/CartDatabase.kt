package com.example.challangebinar3.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cart::class], version = 3)
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
                        //migrasiversion 1 to 2
                        //addMigrations(Migration2to3())
                        .build()
                }
            }
                return INSTANCE as CartDatabase
            }

    }
}

