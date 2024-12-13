package ipca.example.shoppinglistam.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ipca.example.shoppinglistam.ui.theme.ShoppingListAMTheme

@Composable
fun LoginView(modifier: Modifier = Modifier,
              navController: NavHostController
){
    Box (modifier = modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){

        val viewModel : LoginViewModel = viewModel()
        val state = viewModel.state


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = state.email?:"",
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = {
                    Text(text = "insert email")
                })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.password?:"",
                onValueChange = viewModel::onPasswordChange,
                placeholder = {
                    Text(text = "insert password")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.login (
                    onLoginSuccess = {
                        navController
                            .navigate("all_lists")
                    })
                }
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            state.error?.let {
                Text(text = it)
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview(){
    ShoppingListAMTheme {
        LoginView(navController = rememberNavController())
    }
}