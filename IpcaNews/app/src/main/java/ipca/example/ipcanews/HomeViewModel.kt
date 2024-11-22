package ipca.example.ipcanews

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class HomeViewState(
    var articles : ArrayList<Article> = arrayListOf<Article>(),
    var isLoading : Boolean = false,
    var error :String? = null
)

class HomeViewModel : ViewModel() {

    var state = mutableStateOf(HomeViewState())
        private set

    fun fetchArticles() {
        state.value = state.value.copy(isLoading = true)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                state.value = state.value.copy(
                    isLoading = false,
                    error = e.message.toString()
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    state.value = state.value.copy(isLoading = false)
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val result = response.body!!.string()
                    var articlesResult = arrayListOf<Article>()
                    val jsonObject = JSONObject(result)
                    val status = jsonObject.getString("status")
                    if (status == "ok") {
                        val articlesArray = jsonObject.getJSONArray("articles")
                        for (index in 0 until articlesArray.length()) {
                            val articleObject = articlesArray.getJSONObject(index)
                            articlesResult.add(Article.fromJson(articleObject))
                        }
                        state.value = state.value.copy(
                            articles = articlesResult
                        )
                    }
                }
            }
        })
    }
}