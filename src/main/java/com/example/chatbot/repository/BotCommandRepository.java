package com.example.chatbot.repository;

import com.example.chatbot.model.BotCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotCommandRepository extends JpaRepository<BotCommand, Long> {
}