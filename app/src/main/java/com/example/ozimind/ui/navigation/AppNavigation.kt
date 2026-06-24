package com.example.ozimind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ozimind.ui.screens.LandingScreen
import com.example.ozimind.ui.screens.LoginScreen
import com.example.ozimind.ui.screens.RegisterScreen

import com.example.ozimind.ui.screens.WelcomeScreen

import com.example.ozimind.ui.screens.HomeScreen

import com.example.ozimind.ui.screens.ProfileOnboardingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        
        composable("welcome") {
            WelcomeScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        composable("landing") {
            LandingScreen(
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        composable("login") {
            LoginScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToHome = {
                    // Ana sayfaya git ve geri dönmeyi engellemek için Login sayfasını geçmişten sil
                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToHome = {
                    // Kayıt başarılı olduktan sonra hemen Ana Sayfaya DEĞİL, Onboarding (Profil) ekranına git
                    navController.navigate("onboarding") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // Profil Bilgileri Ekranı
        composable("onboarding") {
            ProfileOnboardingScreen(
                onNavigateBack = { navController.popBackStack() }, // Geri tuşu mantığı
                onNavigateToHome = {
                    // İşlem bittikten sonra ana sayfaya git
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // Ana Sayfa (Günlük Listesi)
        composable("home") {
            HomeScreen()
        }
    }
}
