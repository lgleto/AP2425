package ipca.example.ipcanews.ui.components

import android.content.Intent
import android.provider.Settings.Global
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ipca.example.ipcanews.models.Article
import ipca.example.ipcanews.R
import ipca.example.ipcanews.models.AppDatabase
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(article: Article? ){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text =
            if (article == null) "IPCA News"
            else
            article.title?:"",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            article?.let {
                Image(
                    modifier = Modifier.clickable {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, it.url)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, null))
                    },
                    painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription = ""
                )
                IconButton(
                    onClick = {
                        scope.launch(Dispatchers.IO) {
                            AppDatabase.getInstance(context)?.articleDao()?.insert(article)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"

                    )
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview(){
    IpcaNewsTheme {
        MyTopAppBar(article = null)
    }
}
