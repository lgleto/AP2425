package ipca.example.ipcanews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ipca.example.ipcanews.models.Article
import ipca.example.ipcanews.ui.components.MyBottomBar
import ipca.example.ipcanews.ui.components.MyTopAppBar
import ipca.example.ipcanews.ui.favorites.FavoritesView
import ipca.example.ipcanews.ui.home.HomeView
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var articleInFocus by remember { mutableStateOf<Article?>(null) }
            IpcaNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                      MyTopAppBar(articleInFocus)
                    },
                    bottomBar = {
                        MyBottomBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = "home"){
                        composable(route = "home") {
                            articleInFocus = null
                            HomeView(onArticleClick = { article ->
                                navController.navigate("article_detail/${article}")
                            })
                        }
                        composable(route = "favorites") {
                            articleInFocus = null
                            FavoritesView(onArticleClick = { article ->
                                navController.navigate("article_detail/${article}")
                            })
                        }
                        composable(route = "article_detail/{article}") {
                            val articleString = it.arguments?.getString("article")!!
                            val article = Article.fromJson(JSONObject(articleString))
                            articleInFocus = article
                            ArticleDetailView(
                                title = article.title?:"",
                                url = article.url?:"")
                        }
                    }
                }
            }
        }
    }
}
