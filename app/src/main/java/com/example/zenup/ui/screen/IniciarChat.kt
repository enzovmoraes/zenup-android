package com.example.zenup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun IniciarChat(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // Top gradient background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEE9AE5),
                            Color(0xFF5961F9)
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* voltar */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Home",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // White rounded card container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 140.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Olá,\nsou seu assistente de bem-estar.",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Estou aqui para te ouvir e te oferecer apoio emocional, com sugestões e práticas para o seu autocuidado. " +
                        "Você pode me dizer como se sente, me perguntar sobre técnicas de respiração ou buscar um momento de pausa. " +
                        "Lembre-se: estou aqui para te guiar, mas não substituo o apoio de um profissional.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {navController.navigate("Chat")},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C47FF)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text("Iniciar conversa")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIniciarChat() {
    IniciarChat(navController = rememberNavController())
}
