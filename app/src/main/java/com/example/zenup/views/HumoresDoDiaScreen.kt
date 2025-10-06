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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.zenup.R
import com.example.zenup.ui.theme.laranjatitulo


@Composable
fun HumoresDoDiaScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.backgroundscreen),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Conteúdo da tela (o que estava no seu Column original)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título e subtítulo
            Text(
                text = "Humores do Dia",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = laranjatitulo
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Seu dia, seu humor. Sem filtros. Aqui você pode ser totalmente você.",
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
                    MoodGrid()

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

// Criando o grid de botões
@Composable
fun MoodGrid() {
    val moods = listOf("Focado", "Grato", "Confiante", "Inspirado", "Frustrasdo", "Desanimado")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[0], modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[1], modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[2], modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[3], modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodButton(text = moods[4], modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            MoodButton(text = moods[5], modifier = Modifier.weight(1f))
        }
    }
}

// Botão de humor
@Composable
fun MoodButton(text: String, modifier: Modifier = Modifier) {
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
fun PreviewHumoresDoDiaScreen() {
    HumoresDoDiaScreen()
}