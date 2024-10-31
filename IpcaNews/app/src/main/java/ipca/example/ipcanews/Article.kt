package ipca.example.ipcanews

import java.util.Date

class Article {

    var title : String? = null
    var description : String? = null
    var url : String? = null
    var urlToImage : String? = null
    var publishedAt : Date? = null

    constructor(
        title: String?,
        description: String?,
        url: String?,
        urlToImage: String?,
        publishedAt: Date?
    ) {
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
    }
}