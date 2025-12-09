package com.example.zenup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zenup.data.repository.RegistroRepository
import org.example.model.RegistroDiarioRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegistroDiarioState(
    val humor: Int? = null,
    val energia: Int? = null,
    val estresse: Int? = null,
    val idUsuario: Long? = 123L
)

sealed class RegistroApiState {
    object Idle : RegistroApiState()
    object Loading : RegistroApiState()
    object Success : RegistroApiState()   // <<< Sem texto
    data class Error(val message: String) : RegistroApiState()
}

class RegistroDiarioViewModel(
    private val repository: RegistroRepository = RegistroRepository()
) : ViewModel() {

    private val _registroState = MutableStateFlow(RegistroDiarioState())
    val registroState: StateFlow<RegistroDiarioState> = _registroState

    private val _apiState = MutableStateFlow<RegistroApiState>(RegistroApiState.Idle)
    val apiState: StateFlow<RegistroApiState> = _apiState

    fun setIdUsuario(id: Long) {
        _registroState.value = _registroState.value.copy(idUsuario = id)
        Log.d("RegistroViewModel", "ID do usuário definido: $id")
    }

    fun setHumor(humor: Int) {
        _registroState.value = _registroState.value.copy(humor = humor)
        Log.d("RegistroViewModel", "Humor definido: $humor")
    }

    fun setEnergia(energia: Int) {
        _registroState.value = _registroState.value.copy(energia = energia)
        Log.d("RegistroViewModel", "Energia definida: $energia")
    }

    fun setEstresse(estresse: Int) {
        _registroState.value = _registroState.value.copy(estresse = estresse)
        Log.d("RegistroViewModel", "Estresse definido: $estresse")
    }

    fun enviarRegistroDiario() {
        val state = _registroState.value

        if (state.idUsuario == null ||
            state.humor == null ||
            state.energia == null ||
            state.estresse == null
        ) {
            _apiState.value = RegistroApiState.Error("Dados incompletos.")
            return
        }

        val request = RegistroDiarioRequest(
            idUsuario = state.idUsuario,
            humor = state.humor,
            energia = state.energia,
            estresse = state.estresse
        )

        viewModelScope.launch {
            _apiState.value = RegistroApiState.Loading
            try {
                repository.registrarCheckIn(request)
                _apiState.value = RegistroApiState.Success   // <<< sem mensagem
                _registroState.value = RegistroDiarioState(idUsuario = state.idUsuario)
            } catch (e: Exception) {
                _apiState.value = RegistroApiState.Error("Falha ao registrar: ${e.message}")
            }
        }
    }

    fun resetApiState() {
        _apiState.value = RegistroApiState.Idle
    }
}
