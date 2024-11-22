package ipca.example.shoppinglistam.ui.listItems

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import ipca.example.shoppinglistam.models.ListItem

data class AllListState(
    val listItems: List<ListItem> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AllListsViewModel : ViewModel() {

    val db = Firebase.firestore

    var state by mutableStateOf(AllListState())
        private set

    fun loadAllLists() {

        state = state.copy(isLoading = true)

        db.collection("listItems")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    state = state.copy(
                        isLoading = false,
                        error = e.message)
                    return@addSnapshotListener
                }

                val listItems = ArrayList<ListItem>()
                for (doc in value!!) {
                    val listItem = doc.toObject(ListItem::class.java)
                    listItem.docId = doc.id
                    listItems.add(listItem)
                }

                state = state.copy(
                    isLoading = false,
                    error = null,
                    listItems = listItems)
            }
    }

}