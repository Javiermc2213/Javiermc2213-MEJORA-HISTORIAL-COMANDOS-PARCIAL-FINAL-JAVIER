package com.example.chatbot.repository;

import com.example.chatbot.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestRepository extends JpaRepository<RequestModel, Long>{
    
}
