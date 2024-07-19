package com.naflix.streams.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;

public class ConsumeMyMemory {
    public static String getTranslate(String text) {
        ObjectMapper mapper = new ObjectMapper();

        String textToTranslate = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + textToTranslate + "&langpair=" + langpair;

        String json = ConsumeApi.consumeApi(url);

        DataTranslate translate;
        try {
            translate = mapper.readValue(json, DataTranslate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return translate.dataResponse().translatedText();
    }
}
