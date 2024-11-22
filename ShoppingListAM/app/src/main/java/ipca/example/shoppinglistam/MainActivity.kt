package ipca.example.shoppinglistam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import ipca.example.shoppinglistam.ui.listItems.AddListView
import ipca.example.shoppinglistam.ui.listItems.AllListView
import ipca.example.shoppinglistam.ui.login.LoginView
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()
            ShoppingListAMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "login") {
                        composable("all_lists"){
                            AllListView(navController = navController)
                        }
                        composable("add_list"){
                            AddListView(navController = navController)
                        }
                        composable("login") {
                            LoginView( navController = navController)
                        }
                    }
                }
            }

            LaunchedEffect(key1= true) {
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    navController.navigate("all_lists")
                }
            }
        }
    }
}

