// Humor.kt (Versão CORRIGIDA - Compartilha ViewModel)
package com.example.zenup.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel

@Composable
fun Humor(
    navController: NavController,
    viewModel: RegistroDiarioViewModel = viewModel()
) {
    val registroState by viewModel.registroState.collectAsState()
    val humorSelecionado = registroState.humor

    // Define ID do usuário mockado quando a tela é aberta
    LaunchedEffect(Unit) {
        if (registroState.idUsuario == null) {
            viewModel.setIdUsuario(123L) // ID mockado
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.backgroundscreen),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Conteúdo da tela (Columna)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            // Card principal
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F5F9))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Título do card
                    Text(
                        text = "Como você está se sentindo agora?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Grid de botões
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoodButton(
                                text = "Focado",
                                value = 1,
                                isSelected = humorSelecionado == 1,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(1) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            MoodButton(
                                text = "Grato",
                                value = 2,
                                isSelected = humorSelecionado == 2,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(2) }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoodButton(
                                text = "Confiante",
                                value = 3,
                                isSelected = humorSelecionado == 3,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(3) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            MoodButton(
                                text = "Inspirado",
                                value = 4,
                                isSelected = humorSelecionado == 4,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(4) }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MoodButton(
                                text = "Frustrado",
                                value = 5,
                                isSelected = humorSelecionado == 5,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(5) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            MoodButton(
                                text = "Desanimado",
                                value = 6,
                                isSelected = humorSelecionado == 6,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setHumor(6) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Próxima"
                    Button(
                        onClick = { navController.navigate("Energia") },
                        enabled = humorSelecionado != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF773B),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "próxima",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// Botão de humor (com estado de seleção)
@Composable
fun MoodButton(
    text: String,
    value: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) Color(0xFFFF773B) else Color(0xFFE6EDF2)
    val contentColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHumoresDoDiaScreen() {
    Humor(navController = rememberNavController())
}