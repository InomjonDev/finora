package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.SignUpScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          AppNavigation(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  val authViewModel: AuthViewModel = viewModel()
  val context = LocalContext.current

  NavHost(
    navController = navController,
    startDestination = "onboarding",
    modifier = modifier
  ) {
    composable("onboarding") {
      OnboardingScreen(
        onNavigateToLogin = {
          authViewModel.resetState()
          navController.navigate("login")
        },
        onNavigateToSignUp = {
          authViewModel.resetState()
          navController.navigate("signup")
        }
      )
    }
    composable("login") {
      LoginScreen(
        authViewModel = authViewModel,
        onNavigateToSignUp = {
          authViewModel.resetState()
          navController.navigate("signup") {
            popUpTo("onboarding")
          }
        },
        onAuthSuccess = {
          Toast.makeText(context, "Welcome details logged in successfully!", Toast.LENGTH_SHORT).show()
          navController.popBackStack("onboarding", false)
        }
      )
    }
    composable("signup") {
      SignUpScreen(
        authViewModel = authViewModel,
        onNavigateToLogin = {
          authViewModel.resetState()
          navController.navigate("login") {
            popUpTo("onboarding")
          }
        },
        onAuthSuccess = {
          Toast.makeText(context, "Account Created Successfully! Welcome to SpendTrack.", Toast.LENGTH_LONG).show()
          navController.navigate("login") {
            popUpTo("onboarding")
          }
        }
      )
    }
  }
}
