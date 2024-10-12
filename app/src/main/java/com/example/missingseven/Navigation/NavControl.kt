package com.example.missingseven.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.missingseven.Screen.*
import com.example.missingseven.ViewModel.FilterViewModel
import com.example.missingseven.ViewModel.TaskViewModel

/***
 * controller for navigation
 */
class NavControl constructor(
    private val navController: NavHostController,
) {

    @Composable
    fun SetUpNavGraph() {
        val viewModel: TaskViewModel = hiltViewModel()
        val filterViewModel: FilterViewModel = hiltViewModel()
        viewModel.setup(this@NavControl)


        NavHost(navController = navController, startDestination = Screen.Home.route) {

            composable(route = Screen.Home.route){
                HomeScreen(viewModel)
            }

            composable(route = Screen.Task.route){
                if (viewModel.allFetched.value){
                    TaskScreen(viewModel)
                }
            }

            composable(route = Screen.Instruction.route){
                InstructionScreen(viewModel = filterViewModel)
            }
            composable(route = Screen.WorkshopContact.route){
                WorkshopContactScreen(viewModel = viewModel)
            }
            composable(route = Screen.Login.route){
                LoginScreen(viewModel = viewModel)
            }
            composable(route = Screen.Filter.route){
                WaterFilterScreen(
                    task = viewModel.filterUiState,
                    filterViewModel = filterViewModel,
                    navControl = this@NavControl
                )
            }
            composable(route = Screen.Test.route){
                TestScreen(viewModel = filterViewModel){
                    viewModel.onFilterExitClicked()
                }
            }
        }
    }

    fun navigate(backRoute: String, destinationRoute: String){
        navController.navigate(destinationRoute){
            popUpTo(backRoute)
        }
    }


    fun navigateBack(){
        navController.popBackStack()
    }

}


sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Task: Screen("task")
    object Instruction: Screen("instruction")
    object WorkshopContact: Screen("workshop contact")
    object Login: Screen("login")
    object Filter: Screen("filter")
    object Test: Screen("test")

}
