package ipca.example.ipcanews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme
import java.util.Date

@Composable
fun ArticleRowView(article: Article) {
    Row {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.baseline_image_24),
            contentDescription = ""
        )
        Column {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = article.description ?: "")
            Text(text = article.publishedAt.toString() ?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowViewPreview() {
    IpcaNewsTheme {
        ArticleRowView(article = Article(
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = Date()
        ))
    }
}