// Energia.kt (REVISADO para MVVM)
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel

@Composable
fun Energia(
    navController: NavController,
    viewModel: RegistroDiarioViewModel = viewModel()
) {
    val registroState by viewModel.registroState.collectAsState()
    val energiaSelecionada = registroState.energia

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.backgroundscreen),
            contentDescription = "Imagem de fundo",
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
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título do card
                    Text(
                        text = "Qual seu nível\nde energia hoje?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Grid de botões (4 primeiros)
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            EnergyButton(
                                text = "Esgotado",
                                value = 1,
                                isSelected = energiaSelecionada == 1,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setEnergia(1) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            EnergyButton(
                                text = "Baixa",
                                value = 2,
                                isSelected = energiaSelecionada == 2,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setEnergia(2) }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            EnergyButton(
                                text = "Moderada",
                                value = 3,
                                isSelected = energiaSelecionada == 3,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setEnergia(3) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            EnergyButton(
                                text = "Alta",
                                value = 4,
                                isSelected = energiaSelecionada == 4,
                                modifier = Modifier.weight(1f),
                                onClick = { viewModel.setEnergia(4) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Energizado" (centralizado)
                    EnergyButton(
                        text = "Energizado",
                        value = 5,
                        isSelected = energiaSelecionada == 5,
                        modifier = Modifier.width(150.dp),
                        onClick = { viewModel.setEnergia(5) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Próxima"
                    Button(
                        onClick = { navController.navigate("Estresse") },
                        enabled = energiaSelecionada != null,
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

// Botão de energia (com estado de seleção)
@Composable
fun EnergyButton(
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
fun PreviewNivelDeEnergiaScreen() {
    Energia(navController = rememberNavController())
}
