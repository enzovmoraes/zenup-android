package com.example.zenup.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.zenup.R

@Composable
fun TelaInicial(
    onLoginClick: () -> Unit,
    onCadastroClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF8E2DE2), Color(0xFFFF6F00))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "A cada dia,\num novo estado de espírito.",
                fontSize = 22.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Vamos começar?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Não tem uma conta? Cadastre-se",
                color = Color.White,
                modifier = Modifier.clickable { onCadastroClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaInicial() {
    TelaInicial(
        onLoginClick = {},
        onCadastroClick = {}
    )
}