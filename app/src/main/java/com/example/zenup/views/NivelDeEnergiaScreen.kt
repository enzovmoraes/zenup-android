import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zenup.R

@Composable
fun NivelDeEnergiaScreen() {
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
            // Título e subtítulo
            Text(
                text = "Nível de Energia",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Sua vitalidade em movimento. Do seu jeito, no seu ritmo.",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

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
                            EnergyButton(text = "Esgotado", modifier = Modifier.weight(1f))
                            Spacer(modifier = Modifier.width(16.dp))
                            EnergyButton(text = "Baixa", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            EnergyButton(text = "Moderada", modifier = Modifier.weight(1f))
                            Spacer(modifier = Modifier.width(16.dp))
                            EnergyButton(text = "Alta", modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Energizado" (centralizado)
                    EnergyButton(
                        text = "Energizado",
                        modifier = Modifier.width(150.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão "Próxima"
                    Button(
                        onClick = { /* Ação do botão */ },
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

// Botão de energia
@Composable
fun EnergyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Ação do botão */ },
        modifier = modifier
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE6EDF2),
            contentColor = Color.Black
        )
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNivelDeEnergiaScreen() {
    NivelDeEnergiaScreen()
}