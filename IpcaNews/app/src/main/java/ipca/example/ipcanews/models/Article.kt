package ipca.example.ipcanews.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import ipca.example.ipcanews.encodeUrl
import ipca.example.ipcanews.parseServeDate
import ipca.example.ipcanews.toServerString
import org.json.JSONObject
import java.util.Date

@Entity
class Article(
    var title: String?,
    var description: String?,
    @PrimaryKey var url: String = "",
    var urlToImage: String?,
    var publishedAt: Date?
) {

    companion object {
        fun fromJson(jsonObject: JSONObject): Article {
            val title           = jsonObject.getString("title"          )
            val description     = jsonObject.getString("description"    )
            val url             = jsonObject.getString("url"            )
            val urlToImage      = jsonObject.getString("urlToImage"     )
            val publishedAt     = jsonObject.getString("publishedAt"    ).parseServeDate()
            return Article(title, description, url, urlToImage, publishedAt)
        }
    }

    fun toJsonString() : String{
        val jsonObject = JSONObject()
        jsonObject.put("title"       , title       )
        jsonObject.put("description" , description )
        jsonObject.put("url"         , url.encodeUrl())
        jsonObject.put("urlToImage"  , urlToImage?.encodeUrl())
        jsonObject.put("publishedAt" , publishedAt?.toServerString() )

        return jsonObject.toString()
    }

}

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE url = :url")
    fun loadByUrl(url: String): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)
}