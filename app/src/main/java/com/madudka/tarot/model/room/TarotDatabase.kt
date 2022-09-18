package com.madudka.tarot.model.room

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardsEntity::class, CardTypesEntity::class,
    CardInfoEntity::class, ImagesEntity::class,
    LayoutsEntity::class, LayoutsTypesEntity::class],
    exportSchema = false, version = 1)
abstract class TarotDatabase : RoomDatabase() {

    abstract fun getVerifyDao(): VerifyDao
    abstract fun getDayCardDao(): DayCardDao
    abstract fun getCardAdviceDao(): CardAdviceDao

    abstract fun getCardsDao(): CardsDao
    abstract fun getCardFullDao(): CardFullDao

    abstract fun getLayoutsDao(): LayoutsDao

    companion object : SingletonHolder<TarotDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, TarotDatabase::class.java, "tarot.db")
            .createFromAsset("database/tarot.db")
            .fallbackToDestructiveMigration()
            .build()
    })
}