package com.example.chatbot.service;

import com.example.chatbot.model.ClientModel;
import com.example.chatbot.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    IClientRepository clientRepository;
    
    public ArrayList<ClientModel> getClient(){
        return(ArrayList<ClientModel>) clientRepository.findAll();
    } 
    
    public ClientModel saveClient(ClientModel client){
        return clientRepository.save(client);
    }
    
    public Optional<ClientModel> getById(Long id){
        return clientRepository.findById(id);
    }
    
    public ClientModel updateById(ClientModel request, Long id){
        ClientModel client = clientRepository.findById(id).get();
        
        client.setName(request.getName());
        
        return client;
    }
    
    public Boolean deleteClient(Long id){
        try{
           clientRepository.deleteById(id);
           return true;
        }catch(Exception e){
            return false;
        }
    }
}
 