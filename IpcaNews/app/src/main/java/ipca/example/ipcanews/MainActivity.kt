package ipca.example.ipcanews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            IpcaNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = "home"){
                        composable(route = "home") {
                            HomeView(onArticleClick = { title, url ->
                                navController.navigate("article_detail/${title}/${url}")
                            })
                        }
                        composable(route = "article_detail/{title}/{url}") {
                            val title = it.arguments?.getString("title")
                            val url = it.arguments?.getString("url")
                            ArticleDetailView(
                                title = title?:"no title",
                                url = url?:"no_url")
                        }
                    }

                }
            }
        }
    }

}
