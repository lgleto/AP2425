package ipca.example.ipcanews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Date

@Composable
fun HomeView(modifier: Modifier){
    var articles = remember {
        arrayListOf<Article>()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(
            items = articles
        ){index, article ->
            ArticleRowView(article = article)
        }
    }

    LaunchedEffect (key1 = true) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=tesla&from=2024-09-30&sortBy=publishedAt&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()

                    val jsonObject = JSONObject(result)
                    val status = jsonObject.getString("status")
                    if (status == "ok") {
                        val articlesArray = jsonObject.getJSONArray("articles")
                        for (index in 0 until articlesArray.length()) {
                            val articleObject = articlesArray.getJSONObject(index)
                            val title = articleObject.getString("title")
                            val description = articleObject.getString("description")
                            val url = articleObject.getString("url")
                            val urlToImage = articleObject.getString("urlToImage")
                            val publishedAt = articleObject.getString("publishedAt")
                            val article = Article(title, description, url, urlToImage, Date())
                            articlesResult.add(article)
                            println("title: $title")

                        }
                        articles = articlesResult
                    }

                }
            }
        })
    }



}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    IpcaNewsTheme {
        HomeView(Modifier)
    }
}