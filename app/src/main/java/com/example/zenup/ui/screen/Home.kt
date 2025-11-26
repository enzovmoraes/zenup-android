package com.example.zenup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zenup.navigation.Route
import com.example.zenup.ui.theme.roxo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController){

    var currentMonth by remember { mutableStateOf(LocalDate.now()) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("Humor")},
                shape = CircleShape,
                containerColor = Color(0xFF5A4CDE)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Permite que o Spacer empurre o botão para a direita
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Título "Olá, Steven"
                Text(
                    text = "Olá, Steven",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.weight(1f))

                // Botão de Notificação (Sino)
                IconButton(
                    onClick = { /* Ação do botão de notificação */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0x33A69CFF), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificações",
                        tint = Color(0xFF5A4CDE)
                    )
                }
            }


            Text(
                text = "Você já fez\nseu registro hoje?",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = roxo,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Card do calendário
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F5F9))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Título do card e subtítulo
                    Text(
                        text = "Sua jornada até aqui",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Este calendário é a sua história de bem-estar. Caso tenha esquecido algum dia, toque no + para adicionar um check-in.",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Cabeçalho do calendário (Mês e navegação)
                    CalendarHeader(
                        currentMonth = currentMonth,
                        onPreviousMonth = {
                            currentMonth = currentMonth.minusMonths(1)
                        },
                        onNextMonth = {
                            currentMonth = currentMonth.plusMonths(1)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Dias da semana
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        val diasSemana = listOf("S", "T", "Q", "Q", "S", "S", "D")
                        diasSemana.forEach { dia ->
                            Text(text = dia, fontWeight = FontWeight.Bold, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // Grade do calendário
                    CalendarGrid(currentMonth = currentMonth)
                }
            }
        }
    }
}

// Componente da barra de navegação inferior
@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { navController.navigate("home") }
        )

        Spacer(modifier = Modifier.weight(1f))
        NavigationBarItem(
            icon = { Icon(Icons.Default.Chat, contentDescription = "Chat") },
            label = { Text("Chat") },
            selected = false,
            onClick = { navController.navigate(Route.INICIARCHAT)  }
        )
    }
}

@Composable
fun CalendarHeader(
    currentMonth: LocalDate,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            IconButton(onClick = onPreviousMonth) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Mês anterior")
            }
            IconButton(onClick = onNextMonth) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Próximo mês")
            }
        }
    }
}

@Composable
fun CalendarGrid(currentMonth: LocalDate) {
    val firstDayOfMonth = currentMonth.with(TemporalAdjusters.firstDayOfMonth())
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value // 1 (Segunda) a 7 (Domingo)

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(4.dp)
    ) {
        // Ajuste para começar o calendário no dia correto da semana
        val daysToSkip = if (startDayOfWeek == 7) 6 else startDayOfWeek - 1
        items(daysToSkip) {
            Spacer(modifier = Modifier.size(40.dp))
        }

        val daysInMonth = currentMonth.lengthOfMonth()
        items(daysInMonth) { dayOfMonth ->
            val day = dayOfMonth + 1
            DayButton(day = day, isToday = (LocalDate.now().isEqual(currentMonth.withDayOfMonth(day))))
        }
    }
}

@Composable
fun DayButton(day: Int, isToday: Boolean) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isToday) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f)
                    .border(width = 2.dp, color = Color(0xFFFF773B), shape = CircleShape)
            )
        }

        Button(
            onClick = { /* Ação de clique do dia */ },
            shape = CircleShape,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE6EDF2),
                contentColor = Color.Black
            )
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar check-in",
                tint = Color.Gray
            )
        }
        Text(
            text = day.toString(),
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Home(navController = rememberNavController())
}