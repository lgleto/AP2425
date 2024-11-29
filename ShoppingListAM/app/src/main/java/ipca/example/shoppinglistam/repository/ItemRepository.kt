package ipca.example.shoppinglistam.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import ipca.example.shoppinglistam.models.Item
import ipca.example.shoppinglistam.models.ListItem

object ItemRepository {

    val db = Firebase.firestore

    fun add(listId: String, item: Item) {
        db.collection("listItems")
            .document(listId)
            .collection("items")
            .add(item)
    }

    fun remove(listId : String, itemId: String ) {
        db.collection("listItems")
            .document(listId)
            .collection("items")
            .document(itemId)
            .delete()
    }


    fun update(listId : String, item: Item){
        db.collection("listItems")
            .document(listId)
            .collection("items")
            .document(item.docId!!)
            .set(item)
    }


    fun getAll( listId : String, onResult : (List<Item>,String?)->Unit) {

        db.collection("listItems")
            .document(listId)
            .collection("items")
            .addSnapshotListener { value, e ->
                if (e != null) {
                   onResult(emptyList(),e.message)
                    return@addSnapshotListener
                }

                val items = ArrayList<Item>()
                for (doc in value!!) {
                    val item = doc.toObject(Item::class.java)
                    item.docId = doc.id
                    items.add(item)
                }
                onResult(items,null)
            }
    }

}