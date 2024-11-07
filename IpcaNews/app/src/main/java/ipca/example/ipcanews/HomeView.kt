package ipca.example.ipcanews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme

@Composable
fun HomeView(modifier: Modifier = Modifier,
             onArticleClick: (String, String) -> Unit = { _, _ -> } ){

    val viewModel : HomeViewModel = viewModel()
    val articles = viewModel.articles.value

    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(
            items = articles
        ){index, article ->
            ArticleRowView(modifier = Modifier
                .clickable {
                    onArticleClick(article.title?: "",
                        article.url?.encodeUrl()?: "")
                },
                article = article)
        }
    }

    LaunchedEffect (key1 = true) {
        viewModel.fetchArticles()
    }

}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    IpcaNewsTheme {
        HomeView(Modifier)
    }
}