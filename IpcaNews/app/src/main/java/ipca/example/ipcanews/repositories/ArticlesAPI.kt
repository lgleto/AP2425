package ipca.example.ipcanews.repositories

import ipca.example.ipcanews.models.Article
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.jvm.Throws


const val BASE_API = "https://newsapi.org/v2/"
const val API_KEY = "&apiKey=1765f87e4ebc40229e80fd0f75b6416c"

object ArticlesAPI {

    val client = OkHttpClient()

    //top-headlines?country=us
    @Throws(IOException::class)
    suspend fun fetchArticles(path: String): List<Article> {

        val request = Request.Builder()
            .url("$BASE_API$path$API_KEY")
            .build()

        val resultRequest = client.newCall(request).await()

        if (!resultRequest.isSuccessful) throw IOException("Unexpected code ${resultRequest.networkResponse}")
        val result = resultRequest.body!!.string()
        val articlesResult = arrayListOf<Article>()
        val jsonObject = JSONObject(result)
        val status = jsonObject.getString("status")
        if (status == "ok") {
            val articlesArray = jsonObject.getJSONArray("articles")
            for (index in 0 until articlesArray.length()) {
                val articleObject = articlesArray.getJSONObject(index)
                articlesResult.add(Article.fromJson(articleObject))
            }
        }
        return articlesResult

    }


    suspend fun Call.await(recordStack: Boolean = false): Response {
        val callStack = if (recordStack) {
            IOException().apply {
                // Remove unnecessary lines from stacktrace
                // This doesn't remove await$default, but better than nothing
                stackTrace = stackTrace.copyOfRange(1, stackTrace.size)
            }
        } else {
            null
        }
        return suspendCancellableCoroutine { continuation ->
            enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    continuation.resume(response)
                }

                override fun onFailure(call: Call, e: IOException) {
                    // Don't bother with resuming the continuation if it is already cancelled.
                    if (continuation.isCancelled) return
                    callStack?.initCause(e)
                    continuation.resumeWithException(callStack ?: e)
                }
            })

            continuation.invokeOnCancellation {
                try {
                    cancel()
                } catch (ex: Throwable) {
                    //Ignore cancel exception
                }
            }
        }
    }
}