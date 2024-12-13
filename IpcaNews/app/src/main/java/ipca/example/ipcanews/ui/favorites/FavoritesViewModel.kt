package ipca.example.ipcanews.ui.favorites

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ipca.example.ipcanews.models.AppDatabase
import ipca.example.ipcanews.models.Article
import ipca.example.ipcanews.repositories.ArticlesRepository
import ipca.example.ipcanews.repositories.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

data class FavoritesViewState(
    var articles : List<Article> = listOf<Article>(),
    var isLoading : Boolean = false,
    var error :String? = null
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
   private var articlesRepository : ArticlesRepository
)
    : ViewModel() {

    var state = mutableStateOf(FavoritesViewState())
        private set

    fun fetchArticles() {
        articlesRepository.fetchArticlesDB()
            .onEach { result ->
                when(result){
                    is ResultWrapper.Success -> {
                        state.value = state.value.copy(
                            isLoading = false,
                            articles = result.data?: emptyList()
                        )
                    }
                    is ResultWrapper.Loading -> {
                        state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultWrapper.Error -> {
                        state.value = state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}