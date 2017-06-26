/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zonaAzulDigital.entidades.Motorista;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 *
 * @author Samuel
 */
public class MotoristaDeserializer implements JsonDeserializer<Motorista> {
    
    @Override
    public Motorista deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonObject jsonObject = (JsonObject) je;
        
        int id = jsonObject.get("id").getAsInt();
        String nome = jsonObject.get("nome").getAsString();
        String cpf = jsonObject.get("cpf").getAsString();
        BigDecimal credito = jsonObject.get("credito").getAsBigDecimal();
        String senha = jsonObject.get("senha").getAsString();
        
        Motorista m = new Motorista(id,nome, cpf, credito, senha);
        
        return m;
    }
}
