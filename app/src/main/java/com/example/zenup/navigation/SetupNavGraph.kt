package com.example.zenup.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zenup.ui.screen.Chat
import com.example.zenup.ui.screen.Login
import com.example.zenup.ui.screen.TelaInicial
import com.example.zenup.ui.screen.Home
import com.example.zenup.ui.screen.Energia
import com.example.zenup.ui.screen.Estresse
import com.example.zenup.ui.screen.Humor
import com.example.zenup.ui.screen.IniciarChat
import com.example.zenup.ui.screen.ConfirmacaoRegistro
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel

object Route {
    const val TELA_INICIAL = "tela_inicial"
    const val LOGIN = "login"
    const val CADASTRO = "cadastro"
    const val HOME = "home"

    const val HUMOR = "Humor"
    const val ENERGIA = "energia"
    const val ESTRESSE = "estresse"

    const val CONFIRMAR = "ConfirmacaoRegistro"

    const val INICIARCHAT = "iniciarchat"
    const val CHAT = "chat"
}

@Composable
fun SetupNavGraph(navController: NavHostController) {

    val registroViewModel: RegistroDiarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Route.TELA_INICIAL
    ) {
        composable(Route.TELA_INICIAL) {
            TelaInicial(navController)
        }
        composable(Route.LOGIN) {
            Login(navController)
        }
        composable(Route.HOME) {
            Home(navController)
        }

        // Telas do fluxo de registro
        composable(Route.HUMOR) {
            Humor(navController, registroViewModel)
        }
        composable(Route.ENERGIA) {
            Energia(navController, registroViewModel)
        }
        composable(Route.ESTRESSE) {
            Estresse(navController, registroViewModel)
        }

        // ⭐ Tela da confirmação do registro
        composable(Route.CONFIRMAR) {
            ConfirmacaoRegistro(navController)
        }

        // Chat
        composable(Route.INICIARCHAT) {
            IniciarChat(navController)
        }
        composable(Route.CHAT) {
            Chat(navController)
        }
    }
}
