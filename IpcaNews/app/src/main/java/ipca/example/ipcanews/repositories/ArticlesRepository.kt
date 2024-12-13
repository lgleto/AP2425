package ipca.example.ipcanews.repositories

import android.content.Context
import androidx.lifecycle.viewModelScope
import ipca.example.ipcanews.models.AppDatabase
import ipca.example.ipcanews.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val context: Context
) {

    fun fetchArticlesDB() : Flow<ResultWrapper<List<Article>>> =
        flow {
            emit(ResultWrapper.Loading())
            val articles = AppDatabase
                .getInstance(context)
                ?.articleDao()
                ?.getAll()
            emit(ResultWrapper.Success(articles?: emptyList()))
        }.flowOn(Dispatchers.IO)

    fun fetchArticles() : Flow<ResultWrapper<List<Article>>> =
        flow {
            try {
                emit(ResultWrapper.Loading())
                val articles = ArticlesAPI
                    .fetchArticles("top-headlines?country=us")
                emit(ResultWrapper.Success(articles))
            }catch (e:IOException){
                emit(ResultWrapper.Error(e.localizedMessage))
            }
        }.flowOn(Dispatchers.IO)
}