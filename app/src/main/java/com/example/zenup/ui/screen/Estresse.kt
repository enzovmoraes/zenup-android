// Estresse.kt (VERSÃO ATUALIZADA SEM CARD CINZA)
package com.example.zenup.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zenup.R
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel
import com.example.zenup.ui.viewmodel.RegistroApiState


@Composable
fun Estresse(
    navController: NavController,
    viewModel: RegistroDiarioViewModel = viewModel()
) {
    val context = LocalContext.current
    val apiState by viewModel.apiState.collectAsState()
    val registroState by viewModel.registroState.collectAsState()
    val estresseSelecionado = registroState.estresse

    // 🔥 LaunchedEffect ATUALIZADO — sem mensagem de sucesso
    LaunchedEffect(apiState) {
        when (apiState) {
            is RegistroApiState.Success -> {
                // Sem Toast, sem UI — apenas navega
                navController.navigate("ConfirmacaoRegistro") {
                    popUpTo("Humor") { inclusive = true }
                }
                viewModel.resetApiState()
            }

            is RegistroApiState.Error -> {
                Toast.makeText(
                    context,
                    (apiState as RegistroApiState.Error).message,
                    Toast.LENGTH_LONG
                ).show()
                viewModel.resetApiState()
            }

            else -> {}
        }
    }

    val isLoading = apiState is RegistroApiState.Loading
    val isButtonEnabled = !isLoading && estresseSelecionado != null

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.backgroundscreen),
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

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
                    Text(
                        text = "Como está seu\nnível de estresse?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StressButton(
                                text = "Relaxado",
                                value = 1,
                                isSelected = estresseSelecionado == 1,
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                                onClick = { viewModel.setEstresse(1) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            StressButton(
                                text = "Calmo",
                                value = 2,
                                isSelected = estresseSelecionado == 2,
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                                onClick = { viewModel.setEstresse(2) }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StressButton(
                                text = "Moderado",
                                value = 3,
                                isSelected = estresseSelecionado == 3,
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                                onClick = { viewModel.setEstresse(3) }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            StressButton(
                                text = "Estressado",
                                value = 4,
                                isSelected = estresseSelecionado == 4,
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                                onClick = { viewModel.setEstresse(4) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    StressButton(
                        text = "Muito\nestressado",
                        value = 5,
                        isSelected = estresseSelecionado == 5,
                        modifier = Modifier.width(150.dp),
                        enabled = !isLoading,
                        onClick = { viewModel.setEstresse(5) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.enviarRegistroDiario() },
                        enabled = isButtonEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF773B),
                            contentColor = Color.White
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
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
}

@Composable
fun StressButton(
    text: String,
    value: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) Color(0xFFFF773B) else Color(0xFFE6EDF2)
    val contentColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNivelDeEstresseScreen() {
    Estresse(navController = rememberNavController())
}
