package ipca.example.ipcanews.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase? {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "news_db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}