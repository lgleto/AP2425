package ipca.example.ipcanews.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.example.ipcanews.ui.theme.IpcaNewsTheme

data class BottomNavigationItem(
    var title : String,
    var selectedIcon : ImageVector,
    var unSelectedIcon : ImageVector,
    var screen : String
)

@Composable
fun MyBottomBar(
    navController: NavController
){

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    var items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon =  Icons.Outlined.Home,
            screen = "home"
        ),
        BottomNavigationItem(
            title = "Favorites",
            selectedIcon = Icons.Filled.Favorite,
            unSelectedIcon =  Icons.Filled.FavoriteBorder,
            screen = "favorites"
        ),
    )


    BottomAppBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navController.navigate(item.screen)
                },
                label = {
                    Text(item.title)
                },
                icon = {
                    Icon(
                        imageVector = if ( selectedIndex == index)
                            item.selectedIcon else item.unSelectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun MyBottomBarPreview(){
    IpcaNewsTheme {
        MyBottomBar(
            navController = rememberNavController()
        )
    }
}
