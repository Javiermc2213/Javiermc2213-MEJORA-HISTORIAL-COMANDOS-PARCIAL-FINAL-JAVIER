package com.example.chatbot.controller;

import com.example.chatbot.model.ClientModel;
import com.example.chatbot.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/Clients")
@Tag(name = "Client API", description = "API para la gestión de clientes") // Definir el grupo en Swagger
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    public ArrayList<ClientModel> getClient() {
        return this.clientService.getClient();
    }

    @PostMapping
    @Operation(summary = "Guardar un cliente", description = "Permite guardar un nuevo cliente en la base de datos")
    public ClientModel saveClient(@RequestBody ClientModel client) {
        return this.clientService.saveClient(client);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtener un cliente por ID", description = "Retorna un cliente específico usando su ID")
    public Optional<ClientModel> getClientById(
            @Parameter(description = "ID del cliente a buscar", required = true) @PathVariable("id") Long id) {
        return this.clientService.getById(id);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Actualizar un cliente por ID", description = "Actualiza la información de un cliente específico")
    public ClientModel updateClientById(
            @Parameter(description = "Datos del cliente a actualizar", required = true) @RequestBody ClientModel request,
            @Parameter(description = "ID del cliente a actualizar", required = true) @PathVariable("id") Long id) {
        return this.clientService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina un cliente de la base de datos usando su ID")
    public String deleteById(
            @Parameter(description = "ID del cliente a eliminar", required = true) @PathVariable("id") Long id) {
        boolean ok = this.clientService.deleteClient(id);

        if (ok) {
            return "El cliente con el id: " + id + " ha sido borrado";
        } else {
            return "Error: no se puede borrar el cliente";
        }
    }
}
