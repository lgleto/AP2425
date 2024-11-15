package ipca.example.shoppinglistam.ui.listItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.example.shoppinglistam.R
import ipca.example.shoppinglistam.models.ListItem
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

@Composable
fun RowListItem(
    modifier: Modifier = Modifier,
    listItem: ListItem
)  {
    Box (
      modifier = modifier
          .height(60.dp)
          .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
            Text(
                text = listItem.name ?: ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowListItemPreview() {
    ShoppingListAMTheme {
        RowListItem(
            listItem = ListItem(
                name = "Escola",
                icon = R.drawable.ic_launcher_foreground
            )
        )
    }
}