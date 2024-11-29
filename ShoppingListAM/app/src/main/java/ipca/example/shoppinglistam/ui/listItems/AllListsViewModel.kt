package ipca.example.shoppinglistam.ui.listItems

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import ipca.example.shoppinglistam.models.ListItem
import ipca.example.shoppinglistam.repository.ListItemRepository

data class AllListState(
    val listItems: List<ListItem> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AllListsViewModel : ViewModel() {

    var state by mutableStateOf(AllListState())
        private set

    fun loadAllLists() {
        state = state.copy(isLoading = true)
        ListItemRepository.getAll { listItems, error ->
            if (error != null) {
                state = state.copy(
                    isLoading = false,
                    error = error)
                return@getAll
            }
            state = state.copy(
                isLoading = false,
                listItems = listItems)
        }
    }

    fun removeList(listId : String) {
        ListItemRepository.remove(listId)
    }

}