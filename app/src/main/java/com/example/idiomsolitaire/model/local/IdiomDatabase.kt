package com.example.idiomsolitaire.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.idiomsolitaire.App
import java.io.File
import java.io.FileOutputStream


@Database(
    entities = [IdiomInfo::class, IdiomCount::class],
    views = [IdiomResult::class],
    version = IDIOM_DATABASE_VERSION,
    exportSchema = false
)
abstract class IdiomDatabase : RoomDatabase() {

    abstract fun getIdiomDat(): IdiomDao

    companion object {

        @Volatile
        private var instance: IdiomDatabase? = null

        fun getInstance(context: Context): IdiomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): IdiomDatabase {
            installDatabaseFromAssets()
            return Room.databaseBuilder(context, IdiomDatabase::class.java, IDIOM_DATABASE_NAME)
                .build()
        }

        private fun installDatabaseFromAssets() {
            val inputStream = App.instance.assets.open(IDIOM_DATABASE_NAME)

            try {
                val outputFile = File(App.instance.getDatabasePath(IDIOM_DATABASE_NAME).path)
                val outputStream = FileOutputStream(outputFile)

                inputStream.copyTo(outputStream)
                inputStream.close()

                outputStream.flush()
                outputStream.close()
            } catch (exception: Throwable) {
                throw RuntimeException(
                    "The $IDIOM_DATABASE_NAME database couldn't be installed.",
                    exception
                )
            }
        }
    }
}