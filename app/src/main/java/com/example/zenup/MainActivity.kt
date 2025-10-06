package com.example.zenup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zenup.views.Cadastro
import com.example.zenup.views.Login
import com.example.zenup.views.TelaInicial

data class Usuario(
    val email: String,
    val senha: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var usuarios by remember { mutableStateOf(listOf<Usuario>()) }
            var usuarioLogado by remember { mutableStateOf<Usuario?>(null) }

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Telainicial"
            ){
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
                composable(route = "login") {
                    Login(
                        onLoginSuccess = { email, senha ->
                            usuarios = usuarios + Usuario(email, senha)
                            Toast.makeText(this@MainActivity, "Login realizado!", Toast.LENGTH_SHORT).show()
                            navController.navigate("RegistroDiarioScreen")
                        },
                        onCadastroClick = {
                            navController.navigate("cadastro")
                        }
                    )
                }
                this.composable("Cadastro"){
                    Cadastro(
                        onCadastroSuccess = {  email, senha ->
                            usuarios = usuarios + Usuario( email, senha)
                            Toast.makeText(this@MainActivity, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                            navController.navigate("login")
                        },
                        onLoginClick = { navController.navigate("login") }
                    )
                }

            }
        }
    }
}