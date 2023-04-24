package com.billiard.website;

import java.util.HashMap;
import java.util.Map;

public class Word {
    private String name;
    private String error = "Не передан или передан некорректный параметр";
    public Word(String name) {
        this.name = name;
    }
    public Word() {
    }

    public String returnLength() {
        if (name == null || name.length() == 0) {
            return error;
        }
        char[] chars = name.toCharArray();
        boolean isWord = true;
        for (char ch: chars) {
            if (!Character.isAlphabetic(ch)) {
                isWord = false;
            }
        }
        if(isWord) {
            String length = String.valueOf(name.length());
            return length;
        } else {
            return error;
        }
    }
}
