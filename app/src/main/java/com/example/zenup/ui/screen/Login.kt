package com.example.zenup.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.R


@Composable
fun Login(
    navController: NavController
) {

    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var aceitarTermos by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(top = 32.dp, bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        // Título
        Text(
            text = "Entrar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5722),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Nova coluna para alinhar os campos de entrada e seus rótulos à esquerda
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Email
            Text(
                text = "Email",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Senha
            Text(
                text = "Senha",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
            )


            Spacer(modifier = Modifier.height(12.dp))
        } // Fim da nova Column


        Spacer(modifier = Modifier.height(12.dp))

        // Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = aceitarTermos,
                onCheckedChange = { aceitarTermos = it }
            )
            Text(text = "Aceito todos os termos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Login
        Button(
            onClick = {
                if ( email.isNotBlank() && senha.isNotBlank()) {
                    navController.navigate("Home")
                    Toast.makeText(context, "Login realizado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Falha no login", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800),
                contentColor = Color.White
            )
        ) {
            Text(text = "Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link para cadastro
        Text(
            text = "Não tem conta? Cadastre-se agora!",
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                // Aqui você chama a ação de navegação
                navController.navigate("Cadastro")
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TelaLoginPreview() {
    Login(navController = rememberNavController())
}