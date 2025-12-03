package com.example.zenup.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.R
import kotlinx.coroutines.delay

@Composable
fun ConfirmacaoRegistro(navController: NavController) {

    // Efeito para navegar após um pequeno delay
    // 💡 Ajuste para 1500L (1.5 segundos) ou o tempo desejado
    LaunchedEffect(Unit) {
        delay(1500L) // Espera 1.5 segundos
        // Limpa as telas de registro da stack (Humor, Energia, Estresse) e navega para Home
        // Este é o fluxo que a tela Estresse.kt já usa para ir para a Home.
        navController.navigate("Home") {
            popUpTo("Humor") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // 💡 Usando um gradiente similar ao de TelaInicial, mas com cores do app
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF5961F9), Color(0xFFEE9AE5)) // Cores do chat/navegação
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // Imagem de Sucesso (você precisará ter um drawable 'check_success' ou similar)
            // ⚠️ Estou usando o logo como placeholder, substitua por uma imagem de 'sucesso'
            Image(
                painter = painterResource(id = R.drawable.logo), // 👈 MUDAR PARA ICONE DE SUCESSO
                contentDescription = "Sucesso",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Registro Confirmado!",
                fontSize = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold // Fonte mais destacada
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Seu check-in diário foi salvo com sucesso. Você será redirecionado em breve.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmacaoRegistro() {
    ConfirmacaoRegistro(navController = rememberNavController())
}