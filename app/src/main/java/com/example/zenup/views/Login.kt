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
fun Login(){
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        // LINHA NOVA: Adicione a imagem de fundo aqui
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = "Background da tela inicial", // Acessibilidade
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds // Faz a imagem se ajustar ao tamanho da tela
        )

        // Sua coluna com os outros componentes
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(16.dp), // Adicione um padding para não colar na tela
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza o conteúdo horizontalmente
        ){
            // Outros componentes (texto, botões, etc.) vão aqui
            // A sua Column estava sem alinhamento e o padding estava em outro lugar, movi para aqui.
            // O background branco da Column precisa ser removido para a imagem de fundo aparecer
            // Senão, o background branco da Column vai cobrir a imagem.

            // ...
        }
    }


}



@Preview
@Composable
private fun TelaPreview(){
    Login()
}
