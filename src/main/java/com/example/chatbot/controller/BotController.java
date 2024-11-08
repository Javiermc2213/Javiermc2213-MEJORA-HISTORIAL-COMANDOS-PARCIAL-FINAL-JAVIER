package com.example.chatbot.controller;

import com.example.chatbot.model.RequestModel;
import com.example.chatbot.service.ChatGptService;
import com.example.chatbot.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotController extends TelegramLongPollingBot {

    @Autowired
    private RequestService RequestService;

    @Autowired
    private ChatGptService chatGptService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();

            // Lógica para obtener respuesta de ChatGPT
            String response = chatGptService.getChatGptResponse(userMessage);

            // Guardar la interacción en la base de datos
            RequestModel request = new RequestModel();
            request.setQuestion(userMessage);
            request.setResponse(response); // Guarda la respuesta del chatbot
            RequestService.saveRequest(request);
           // requestService.save(request);

            // Enviar respuesta al usuario
            sendResponse(chatId, response);
        }
    }

    private void sendResponse(String chatId, String response) {
        // Implementa aquí la lógica para enviar un mensaje al usuario
         SendMessage message = new SendMessage();
         message.setChatId(chatId);        // Asigna el chatId del usuario
         message.setText(response);        // Asigna el texto de la respuesta

         try {
             execute(message);             // Envía el mensaje al usuario
         } catch (TelegramApiException e) {
             e.printStackTrace();             // Manejo de la excepción en caso de error
        }
    }

    @Override
    public String getBotUsername() {
        return "TuBotUsername"; // Reemplaza con tu nombre de bot
    }

    @Override
    public String getBotToken() {
        return "TuBotToken"; // Reemplaza con tu token de bot
    }
}
