package ipca.example.ipcanews

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var topTitle by remember { mutableStateOf("IPCA News") }
            var urlToShare by remember { mutableStateOf("") }
            IpcaNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            title = {
                            Text(text = topTitle,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                                )
                        },
                            actions = {
                                if (urlToShare.isNotEmpty()) {
                                    Image(
                                        modifier = Modifier.clickable {
                                            val sendIntent = Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(Intent.EXTRA_TEXT, urlToShare)
                                                type = "text/plain"
                                            }
                                            startActivity(Intent.createChooser(sendIntent, null))
                                        },
                                        painter = painterResource(id = R.drawable.baseline_share_24),
                                        contentDescription = ""
                                    )
                                }
                            })
                    },
                    bottomBar = {

                    }
                ) { innerPadding ->
                    NavHost(navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = "home"){
                        composable(route = "home") {
                            topTitle = "IPCA News"
                            urlToShare = ""
                            HomeView(onArticleClick = { title, url ->
                                navController.navigate("article_detail/${title}/${url}")
                            })
                        }
                        composable(route = "article_detail/{title}/{url}") {
                            val title = it.arguments?.getString("title")
                            val url = it.arguments?.getString("url")
                            topTitle = title?:""
                            urlToShare = url?:""
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
