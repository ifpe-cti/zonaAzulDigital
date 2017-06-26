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
import com.zonaAzulDigital.entidades.Placa;
import java.lang.reflect.Type;

/**
 *
 * @author Samuel
 */
public class PlacaDeserializer implements JsonDeserializer<Placa> {

    @Override
    public Placa deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonObject jsonObject = (JsonObject) je;

        String numeros = jsonObject.get("numeros").getAsString();
        String letras = jsonObject.get("letras").getAsString();

        Placa p = new Placa(letras, numeros);
        return p;
    }

}
