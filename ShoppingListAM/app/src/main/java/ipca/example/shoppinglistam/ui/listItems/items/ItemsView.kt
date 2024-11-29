package ipca.example.shoppinglistam.ui.listItems.items

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
import ipca.example.shoppinglistam.models.Item
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme


@Composable
fun ItemsView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController){

    val viewModel : ItemsViewModel = viewModel()
    val state = viewModel.state


    ItemsViewContent(
        modifier = modifier,
        state = state,
        navController = navController,
        onCheckItem = {
            viewModel.check(listId, it)
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getAll(listId)
    }
}

@Composable
fun ItemsViewContent(
    modifier: Modifier = Modifier,
    state : ItemsState,
    navController : NavController,
    onCheckItem : (Item)->Unit
){
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
                    items = state.items
                ) { index, item ->
                    RowItem(item = item) {
                        onCheckItem(item)
                    }
                }
            }
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                navController.navigate("add_item")
            }) {
            Text("ADD ITEM")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemsViewPreview(){
    ShoppingListAMTheme {
        ItemsViewContent(
            state = ItemsState(
                items = arrayListOf(Item("", "Bataras", 10.0, false)),
                error = null,
                isLoading = false
            ),
            navController = rememberNavController(),
            onCheckItem = {}
        )
    }
}