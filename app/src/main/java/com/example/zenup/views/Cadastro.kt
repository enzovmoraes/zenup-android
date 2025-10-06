package com.example.zenup.views

import com.example.zenup.R

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(onLoginClick: () -> Unit) {

    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var aceitarTermos by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Logo e Título centralizados
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(top = 32.dp, bottom = 16.dp)
        )

        Text(
            text = "Crie sua conta",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5722),
            modifier = Modifier.padding(bottom = 24.dp)
        )


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Nome
            Text(
                text = "Nome completo",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email
            Text(
                text = "Email",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Confirmar senha
            Text(
                text = "Confirme sua senha",
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        // Checkbox e Botão centralizados
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = aceitarTermos,
                onCheckedChange = { aceitarTermos = it }
            )
            Text(text = "Aceito todos os termos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    nome.isBlank() || email.isBlank() || senha.isBlank() || confirmarSenha.isBlank() ->
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()

                    senha != confirmarSenha ->
                        Toast.makeText(context, "As senhas não coincidem", Toast.LENGTH_SHORT).show()

                    !aceitarTermos ->
                        Toast.makeText(context, "Você precisa aceitar os termos", Toast.LENGTH_SHORT).show()

                    else ->
                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
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
            Text(text = "Cadastrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Já tem sua conta? Entre agora!",
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Ir para tela de login", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCadastro() {
    Cadastro (
        onLoginClick = {}
    )
}