package com.example.zenup.ui.viewmodel

import kotlinx.coroutines.delayimport kotlinx.coroutines.flow.MutableStateFlowimport kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(text: String) {
        val msg = ChatMessage(text, true)
        _messages.value += msg

        viewModelScope.launch {
            delay(600)
            val bot = ChatMessage("Você disse: $text", false)
            _messages.value += bot
        }
    }
}