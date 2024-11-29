package ipca.example.shoppinglistam.ui.listItems.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ipca.example.shoppinglistam.models.Item
import ipca.example.shoppinglistam.repository.ItemRepository

data class ItemsState(
    val items: List<Item> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ItemsViewModel  : ViewModel(){

    var state by mutableStateOf(ItemsState())
        private set

    fun getAll(listId:String){
        state = state.copy( isLoading = true)
        ItemRepository.getAll(listId) { items , error ->
            if (error != null) {
                state = state.copy(
                    isLoading = false,
                    error = error)
                return@getAll
            }
            state = state.copy(
                isLoading = false,
                items = items)
        }
    }

    fun check(listId:String, item : Item) {


        item.checked = !(item.checked?:false)

        ItemRepository.update(listId, item)
    }
}