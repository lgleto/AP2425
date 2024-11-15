package ipca.example.shoppinglistam.ui.listItems

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ipca.example.shoppinglistam.R
import ipca.example.shoppinglistam.models.ListItem
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

@Composable
fun AllListView(modifier: Modifier = Modifier,
                onClickListItem: (String?) -> Unit = {}
) {

    val viewModel : AllListsViewModel = viewModel()
    val state = viewModel.state


    AllListViewContent(
        state = state
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllLists()
    }

}

@Composable
fun AllListViewContent(modifier: Modifier = Modifier,
                       state: AllListState,
                       onClickListItem: (String?) -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator()

        } else if (state.error != null) {
            Text(text = state.error)
        } else {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                itemsIndexed(
                    items = state.listItems
                ) { index, item ->
                    RowListItem(
                        modifier = Modifier
                            .clickable {
                                onClickListItem(item.docId)
                            },
                        listItem = item
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllListViewPreview() {
    ShoppingListAMTheme {
        AllListViewContent(
            state =
            AllListState(
                isLoading = false,
                error = "internwr error",
                listItems = listOf(
                    ListItem(
                        name = "Escola",
                        icon = R.drawable.ic_launcher_foreground
                    )
            )

            )
        )
    }
}