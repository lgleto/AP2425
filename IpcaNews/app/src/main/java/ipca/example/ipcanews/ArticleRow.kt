package ipca.example.ipcanews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import ipca.example.ipcanews.ui.theme.IpcaNewsTheme
import java.util.Date

@Composable
fun ArticleRowView(article: Article) {
    Row {
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = article.urlToImage,
            //painter = painterResource(id = R.drawable.baseline_image_24),
            placeholder = painterResource(id = R.drawable.baseline_image_24), // Placeholder color
            error = ColorPainter(Color.Red),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Column {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = article.description ?: "")
            Text(text = article.publishedAt?.toShortString()?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowViewPreview() {
    IpcaNewsTheme {
        ArticleRowView(article = Article(
            title = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin molestie ligula nec dignissim tincidunt. Phasellus libero nisl, tempor eget enim sed, vulputate suscipit purus. Nulla a nunc felis. Proin blandit vehicula justo a pellentesque. Cras id rutrum felis. Aenean nec cursus odio. Quisque rhoncus neque pulvinar fringilla ullamcorper. Donec interdum urna sit amet erat condimentum rhoncus. Integer tincidunt lectus vitae augue gravida, in ultricies risus rutrum. Quisque semper, tellus in imperdiet placerat, turpis est fringilla justo, hendrerit accumsan augue nisl sed tortor.",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = Date()
        ))
    }
}