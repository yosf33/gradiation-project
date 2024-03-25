//1.add Bottom bar screens:
sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:ImageVector,
){

object Home:BottomBarScreen(
    route = "home",
    title = "Home",
    icon = Icons.Default.Home
)

object AddPost:BottomBarScreen(
    route = "addPost",
    title = "AddPost",
    icon = Icons.Default.Add
)

object Chat:BottomBarScreen(
    route = "chat",
    title = "Chat",
    icon = Icons.Default.ChatBubble
)
object Profile:BottomBarScreen(
    route = "profile",
    title = "Profile",
    icon = Icons.Default.Person
)
object Settings:BottomBarScreen(
    route = "settings",
    title = "Settings",
    icon = Icons.Default.Settings
)

} 

//2.create Bottomnavgraph:
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {

        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }

        composable(route = BottomBarScreen.AddPost.route) {
            AddPostScreen()
        }

        composable(route = BottomBarScreen.Chat.route) {
            ChatListScreen()
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }

        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}

//3.implement main screen:
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        it
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screen = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Chat,
        BottomBarScreen.AddPost,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            clip=true
        }, backgroundColor = secondary

    ) {
        screen.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}

//4.implement each screen a.homescreen b.addpostscreen c.settingscreen