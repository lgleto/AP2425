package ipca.example.ipcanews.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ipca.example.ipcanews.ArticleRowView
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme

@Composable
fun FavoritesView(modifier: Modifier = Modifier,
             onArticleClick: (String) -> Unit = { _ -> } ){

    val viewModel : FavoritesViewModel = viewModel()
    val state = viewModel.state

    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else if (state.value.error != null) {
            Text(text = state.value.error?:"")
        } else if (state.value.articles.isEmpty()){
            Text(text = "No articles!")
        } else
            LazyColumn(modifier = modifier.fillMaxSize()) {
                itemsIndexed(
                    items = state.value.articles
                ){index, article ->
                    ArticleRowView(modifier = Modifier
                        .clickable {
                            onArticleClick(article.toJsonString())
                        },
                        article = article)
                }
            }
    }


    LaunchedEffect (key1 = true) {
        viewModel.fetchArticles(context)
    }

}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    IpcaNewsTheme {
        FavoritesView(Modifier)
    }
}