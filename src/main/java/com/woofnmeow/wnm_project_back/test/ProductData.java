package com.woofnmeow.wnm_project_back.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ProductData {
    public JsonArray crowling() throws FileNotFoundException {
        FileReader fileReader = new FileReader("C:/aws/project/wnm_project_back/src/main/java/com/woofnmeow/wnm_project_back/test/Unit5.json");
        JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();
        return jsonArray;
    }
}
