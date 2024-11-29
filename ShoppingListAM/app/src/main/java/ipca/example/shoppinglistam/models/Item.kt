package ipca.example.shoppinglistam.models

data class Item (
    var docId : String? = null,
    var name : String? = null,
    var qtd : Double? = null,
    var checked : Boolean? = null
)