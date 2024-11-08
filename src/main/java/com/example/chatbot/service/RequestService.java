package com.example.chatbot.service;

import com.example.chatbot.model.RequestModel;
import com.example.chatbot.repository.IRequestRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
   
    @Autowired
    IRequestRepository requestRepository;
    
    public ArrayList<RequestModel> getRequest(){
        return(ArrayList<RequestModel>) requestRepository.findAll();
    }
    
     public RequestModel saveRequest(RequestModel request){
        return requestRepository.save(request);
    }
     
    public Optional<RequestModel> getById(Long id){
        return requestRepository.findById(id);
    }
    
    public Boolean deleteRequest(Long id){
        try{
           requestRepository.deleteById(id);
           return true;
        }catch(Exception e){
            return false;
        }
    }
}
