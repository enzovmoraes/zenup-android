// Estresse.kt (Versão FINAL E CORRIGIDA para MVVM/Envio de API)
package com.example.zenup.ui.screen

import android.widget.Toast // 👈 Corrigido: Import para Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator // 👈 Corrigido: Import para Loading
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect // 👈 Corrigido: Import para Efeitos Colaterais
import androidx.compose.runtime.collectAsState // 👈 Corrigido: Import para Observação de Flow
import androidx.compose.runtime.getValue // 👈 Corrigido: Import para delegate
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext // 👈 Corrigido: Import para Contexto
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zenup.R
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel // 👈 Corrigido: Import para ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.ui.theme.laranjatitulo
import com.example.zenup.ui.viewmodel.RegistroDiarioViewModel // 👈 Import
import com.example.zenup.ui.viewmodel.RegistroApiState // 👈 Import


@Composable
fun Estresse(
    navController: NavController,
    viewModel: RegistroDiarioViewModel = viewModel() // 👈 Injeção do ViewModel
) {
    val context = LocalContext.current
    val apiState by viewModel.apiState.collectAsState()
    val registroState by viewModel.registroState.collectAsState()
    val estresseSelecionado = registroState.estresse

    // Efeito colateral: reage às mudanças no apiState (Sucesso, Erro, Loading)
    LaunchedEffect(apiState) {
        when (apiState) {
            is RegistroApiState.Success -> {
                Toast.makeText(context, (apiState as RegistroApiState.Success).message, Toast.LENGTH_LONG).show()
                // Limpa as telas de registro da stack e navega para Home
                navController.navigate("Home") {
                    // 👈 Corrigido: popUpTo exige um lambda de navegação
                    popUpTo("Humor") { inclusive = true }
                }
                viewModel.resetApiState()
            }
            is RegistroApiState.Error -> {
                Toast.makeText(context, (apiState as RegistroApiState.Error).message, Toast.LENGTH_LONG).show()
                viewModel.resetApiState()
            }
            else -> { /* Nada para Idle */ }
        }
    }

    val isLoading = apiState is RegistroApiState.Loading
    // Habilita o botão se não estiver carregando E se um estresse foi selecionado
    val isButtonEnabled = !isLoading && estresseSelecionado != null

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
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título do card
                    Text(
                        text = "Como está seu\nnível de estresse?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Grid de botões
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StressButton(text = "Relaxado", isSelected = estresseSelecionado == "Relaxado", modifier = Modifier.weight(1f), enabled = !isLoading, onClick = { viewModel.setEstresse("Relaxado") })
                            Spacer(modifier = Modifier.width(16.dp))
                            StressButton(text = "Calmo", isSelected = estresseSelecionado == "Calmo", modifier = Modifier.weight(1f), enabled = !isLoading, onClick = { viewModel.setEstresse("Calmo") })
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StressButton(text = "Moderado", isSelected = estresseSelecionado == "Moderado", modifier = Modifier.weight(1f), enabled = !isLoading, onClick = { viewModel.setEstresse("Moderado") })
                            Spacer(modifier = Modifier.width(16.dp))
                            StressButton(text = "Estressado", isSelected = estresseSelecionado == "Estressado", modifier = Modifier.weight(1f), enabled = !isLoading, onClick = { viewModel.setEstresse("Estressado") })
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Muito estressado"
                    StressButton(
                        text = "Muito\nestressado",
                        isSelected = estresseSelecionado == "Muito estressado",
                        modifier = Modifier.width(150.dp),
                        enabled = !isLoading,
                        onClick = { viewModel.setEstresse("Muito estressado") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Próxima" -> FINALIZAR REGISTRO
                    Button(
                        onClick = {
                            viewModel.enviarRegistroDiario() // 👈 DISPARA A CHAMADA FINAL À API
                        },
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
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                        } else {
                            Text(
                                text = "FINALIZAR REGISTRO",
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

// Botão de estresse (com estado de seleção)
@Composable
fun StressButton(text: String, isSelected: Boolean, modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    // 👈 Definição de cores baseada no estado de seleção
    val containerColor = if (isSelected) Color(0xFFFF773B) else Color(0xFFE6EDF2)
    // Se o botão estiver desabilitado (enquanto carrega), ele deve ter uma cor ligeiramente diferente.
    val actualContainerColor = if (!enabled) Color.Gray.copy(alpha = 0.5f) else containerColor
    val contentColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = actualContainerColor,
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