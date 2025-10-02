package com.example.zenup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zenup.views.Cadastro
import com.example.zenup.views.Login
import com.example.zenup.views.TelaInicial


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Telainicial"){
                this.composable("TelaInicial"){
                    TelaInicial(
                        onLoginClick = {
                            navController.navigate("Login")
                        },
                        onCadastroClick = {
                            navController.navigate("Cadastro")
                        }
                    )
                }
                this.composable("Login"){
                    Login(
                        onCadastroClick = {
                            navController.navigate("Cadastro")
                        }
                    )
                }
                this.composable("Cadastro"){
                    Cadastro(
                        onLoginClick = {
                            navController.navigate("TelaInicial")
                        }
                    )
                }
            }
        }
    }
}