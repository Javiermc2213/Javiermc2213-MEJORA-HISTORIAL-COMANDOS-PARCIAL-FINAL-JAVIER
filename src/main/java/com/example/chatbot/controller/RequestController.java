package com.example.chatbot.controller;

import com.example.chatbot.model.RequestModel;
import com.example.chatbot.service.RequestService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Request")
public class RequestController {
   @Autowired
   private RequestService requestService;
   
   @GetMapping
   public ArrayList<RequestModel> getRequest(){
      return this.requestService.getRequest();
   } 
   
   @PostMapping
   public RequestModel saveRequest(@RequestBody RequestModel request){
       return this.requestService.saveRequest(request);
   }
   
   @GetMapping(path = "/{id}")
   public Optional<RequestModel> getClientById(@PathVariable Long id){
       return this.requestService.getById(id);
   }
   
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
       boolean ok = this.requestService.deleteRequest(id);
       
       if(ok == true){
           return "El cliente con el id: " + id +" ha sido borrado";
       }else{
         return "Error: no se puede borrar el cliente";  
       }
   }
}
