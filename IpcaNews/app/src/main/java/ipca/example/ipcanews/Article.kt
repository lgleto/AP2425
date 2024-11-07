package ipca.example.ipcanews

import org.json.JSONObject
import java.util.Date

class Article(
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: Date?
) {

    companion object {
        fun fromJson(jsonObject: JSONObject): Article {
            val title = jsonObject.getString("title")
            val description = jsonObject.getString("description")
            val url = jsonObject.getString("url")
            val urlToImage = jsonObject.getString("urlToImage")
            val publishedAt = jsonObject.getString("publishedAt").parseServeDate()
            return Article(title, description, url, urlToImage, publishedAt)
        }
    }

}