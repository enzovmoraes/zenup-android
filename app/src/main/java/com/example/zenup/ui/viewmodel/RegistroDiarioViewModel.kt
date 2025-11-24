// RegistroDiarioViewModel.kt (Versão CORRIGIDA e COMPLETA)
package com.example.zenup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zenup.data.model.RegistroDiarioRequest
import com.example.zenup.data.model.CadastroRequest
import com.example.zenup.data.repository.AuthRepository
import com.example.zenup.data.repository.RegistroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// 1. Estado para Gerenciar os Inputs do Usuário nas 3 Telas
data class RegistroDiarioState(
    val humor: String? = null,
    val energia: String? = null,
    val estresse: String? = null,
    val userId: Int? = null // ID do usuário autenticado
)

// 2. Estado para Gerenciar a Comunicação Final com a API
sealed class RegistroApiState {
    object Idle : RegistroApiState()
    object Loading : RegistroApiState()
    data class Success(val message: String) : RegistroApiState()
    data class Error(val message: String) : RegistroApiState()
}

class RegistroDiarioViewModel(
    private val repository: RegistroRepository = RegistroRepository()
) : ViewModel() {

    // Mantém o estado dos inputs do usuário nas 3 telas
    private val _registroState = MutableStateFlow(RegistroDiarioState())
    val registroState: StateFlow<RegistroDiarioState> = _registroState

    // Mantém o estado da chamada final à API
    private val _apiState = MutableStateFlow<RegistroApiState>(RegistroApiState.Idle)
    val apiState: StateFlow<RegistroApiState> = _apiState

    // Funções para salvar o input de cada tela
    fun setUserId(id: Int) {
        _registroState.value = _registroState.value.copy(userId = id)
    }

    fun setHumor(humor: String) {
        // 👈 CRÍTICO: Usa .copy() para emitir um novo estado, forçando a recomposição do Compose.
        _registroState.value = _registroState.value.copy(humor = humor)
    }

    fun setEnergia(energia: String) {
        // 👈 CRÍTICO: Usa .copy()
        _registroState.value = _registroState.value.copy(energia = energia)
    }

    fun setEstresse(estresse: String) {
        // 👈 CRÍTICO: Usa .copy()
        _registroState.value = _registroState.value.copy(estresse = estresse)
    }

    // Função para enviar o registro completo ao final
    fun enviarRegistroDiario() {
        val state = _registroState.value

        // Validação
        if (state.userId == null || state.humor == null || state.energia == null || state.estresse == null) {
            _apiState.value = RegistroApiState.Error("Dados incompletos para o registro. Certifique-se de que o ID do usuário foi setado após o login.")
            return
        }

        val request = RegistroDiarioRequest(
            userId = state.userId,
            humor = state.humor,
            energia = state.energia,
            estresse = state.estresse
        )

        viewModelScope.launch {
            _apiState.value = RegistroApiState.Loading
            try {
                val response = repository.registrarCheckIn(request)
                _apiState.value = RegistroApiState.Success(response.mensagem)
                _registroState.value = RegistroDiarioState(userId = state.userId)
            } catch (e: Exception) {
                _apiState.value = RegistroApiState.Error("Falha ao registrar: ${e.message ?: "Erro desconhecido"}")
            }
        }
    }

    fun resetApiState() {
        _apiState.value = RegistroApiState.Idle
    }
}