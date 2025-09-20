package com.example.zenup.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zenup.R
import com.example.zenup.ui.theme.azul
import com.example.zenup.ui.theme.azulclaro
import com.example.zenup.ui.theme.branco



@Composable
fun TelaInicial(){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = branco),
    ) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
            color = branco
        ).verticalScroll(rememberScrollState())
    ){

        Image(
            painter = painterResource(id = R.drawable.estrela),
            contentDescription = null,
            modifier = Modifier.width(width = 750.dp).height(550.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Text(
            text = buildAnnotatedString {
                append("A cada dia,")
                withStyle(
                    style = SpanStyle(
                        color = azulclaro
                    )
                ){
                    append("\num novo estado")
                }
                append("\nde espírito")
            },
            color = azul,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp),
       )
        Button(
            onClick = { /* ação do botão */ },
            modifier = Modifier
                .fillMaxWidth(0.9f)   // ocupa 90% da largura
                .height(50.dp),       // altura fixa
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF7043), // cor de fundo (laranja, como no exemplo)
                contentColor = Color.White          // cor do texto
            ),
            shape = RoundedCornerShape(12.dp) // bordas arredondadas
        ) {
            Text(
                text = "Vamos começar?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
     }
   }

@Preview
@Composable
private fun TelaPreview(){
    TelaInicial()
}