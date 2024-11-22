package ipca.example.shoppinglistam.ui.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.example.shoppinglistam.R
import ipca.example.shoppinglistam.models.ListItem
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

@Composable
fun AllListView(modifier: Modifier = Modifier,
                navController : NavController = rememberNavController(),
                onClickListItem: (String?) -> Unit = {}
) {

    val viewModel : AllListsViewModel = viewModel()
    val state = viewModel.state


    AllListViewContent(
        state = state,
        navController = navController
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllLists()
    }

}

@Composable
fun AllListViewContent(modifier: Modifier = Modifier,
                       navController : NavController = rememberNavController(),
                       state: AllListState,
                       onClickListItem: (String?) -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd) {
        if (state.isLoading) {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = state.error)
            }
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
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
            navController.navigate("add_list")
        }) {
            Text("ADD LIST")
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
                        icon = 0L
                    )
            )
            )
        )
    }
}