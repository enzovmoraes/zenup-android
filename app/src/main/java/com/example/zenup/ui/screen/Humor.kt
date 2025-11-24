// Humor.kt (REVISADO para MVVM)
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.zenup.ui.theme.laranjatitulo
import androidx.lifecycle.viewmodel.compose.viewModel // Novo Import
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel // Novo Import

@Composable
fun Humor(
    navController: NavController,
    viewModel: RegistroDiarioViewModel = viewModel() // 👈 Injeção do ViewModel
) {
    // Observa o estado atual do humor no ViewModel para destacar o botão
    val registroState by viewModel.registroState.collectAsState()
    val humorSelecionado = registroState.humor

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.backgroundscreen),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ... (Título e subtítulo)

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
                    MoodGrid(
                        humorSelecionado = humorSelecionado,
                        onMoodSelected = { humor ->
                            viewModel.setHumor(humor) // 👈 Salva o humor no ViewModel
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Próxima"
                    Button(
                        onClick = { navController.navigate("Energia") },
                        enabled = humorSelecionado != null, // 👈 Só habilita se um humor for selecionado
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

@Composable
fun MoodGrid(humorSelecionado: String?, onMoodSelected: (String) -> Unit) {
    val moods = listOf("Focado", "Grato", "Confiante", "Inspirado", "Frustrasdo", "Desanimado")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[0], isSelected = humorSelecionado == moods[0], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[0]) })
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[1], isSelected = humorSelecionado == moods[1], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[1]) })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[2], isSelected = humorSelecionado == moods[2], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[2]) })
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[3], isSelected = humorSelecionado == moods[3], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[3]) })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[4], isSelected = humorSelecionado == moods[4], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[4]) })
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[5], isSelected = humorSelecionado == moods[5], modifier = Modifier.weight(1f), onClick = { onMoodSelected(moods[5]) })
        }
    }
}

// Botão de humor (com estado de seleção)
@Composable
fun MoodButton(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    // 👈 Definição de cores baseada no estado de seleção
    val containerColor = if (isSelected) Color(0xFFFF773B) else Color(0xFFE6EDF2)
    val contentColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        modifier = modifier
            .height(100.dp),
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