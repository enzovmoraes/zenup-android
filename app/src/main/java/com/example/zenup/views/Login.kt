package com.example.zenup.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zenup.R
import com.example.zenup.ui.theme.azul
import com.example.zenup.ui.theme.azulclaro
import com.example.zenup.ui.theme.branco
import com.example.zenup.ui.theme.laranja
import com.example.zenup.ui.theme.textodestaque


@Composable
fun Login() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = "Background da tela inicial",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(350.dp))

            // REMOVEMOS AS LINHAS DOS TEXTOS BEM-VINDO E ENTRAR AQUI

            // Formulário de login
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de Email
                OutlinedTextField(
                    value = "exemplo@exemplo.com",
                    onValueChange = { /* Atualize o state */ },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = azul,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = azul
                    )
                )

                // Campo de Senha
                OutlinedTextField(
                    value = "Exemplo123@@",
                    onValueChange = { /* Atualize o state */ },
                    label = { Text("Senha") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = azul,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = azul
                    )
                )

                Text(
                    text = "Esqueceu sua senha?",
                    color = azul,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp)
                )
            }

            // Botão "Entrar"
            Button(
                onClick = { /* Ação de login */ },
                colors = ButtonDefaults.buttonColors(containerColor = laranja),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Entrar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            // Texto "Não tem uma conta? Cadastre-se"
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 14.sp)) {
                        append("Não tem uma conta? ")
                    }
                    withStyle(style = SpanStyle(color = azul, fontWeight = FontWeight.Bold)) {
                        append("Cadastre-se")
                    }
                },
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
    }
}


@Preview
@Composable
private fun TelaPreview(){
    Login()
}
