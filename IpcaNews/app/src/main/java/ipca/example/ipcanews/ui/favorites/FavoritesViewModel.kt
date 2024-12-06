package ipca.example.ipcanews.ui.favorites

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.ipcanews.models.AppDatabase
import ipca.example.ipcanews.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class FavoritesViewState(
    var articles : List<Article> = listOf<Article>(),
    var isLoading : Boolean = false,
    var error :String? = null
)

class FavoritesViewModel : ViewModel() {

    var state = mutableStateOf(FavoritesViewState())
        private set

    fun fetchArticles(context: Context) {



      viewModelScope.launch(Dispatchers.IO) {
          var articles = AppDatabase
              .getInstance(context)
              ?.articleDao()
              ?.getAll()
          viewModelScope.launch(Dispatchers.Main) {
              state.value = state.value.copy(
                  isLoading = false,
                  articles = articles?: emptyList()
              )
          }
      }

    }
}