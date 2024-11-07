package ipca.example.ipcanews

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.parseServeDate() : Date? {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
        Locale.getDefault())
    return format.parse(this)
}

fun Date.toShortString() : String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(this)
}