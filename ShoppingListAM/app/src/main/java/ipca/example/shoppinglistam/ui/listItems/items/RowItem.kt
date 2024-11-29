package ipca.example.shoppinglistam.ui.listItems.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.example.shoppinglistam.R
import ipca.example.shoppinglistam.models.Item
import ipca.example.shoppinglistam.models.ListItem
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    item: Item,
    onCheck : ()->Unit
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
            Text(
                text = item.name ?: ""
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = item.qtd.toString()
            )
            Checkbox(
                checked = (item.checked?:false),
                onCheckedChange = { onCheck() } )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun RowListItemPreview() {
    ShoppingListAMTheme {
        RowItem(
            item = Item(
                name = "Escola",
                qtd = 10.0,
                checked = true
            ),
            onCheck = {}
        )
    }
}