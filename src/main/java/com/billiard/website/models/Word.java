package com.billiard.website.models;

import java.util.HashMap;
import java.util.Map;

public class Word {
    private String word;
    private String error = "Не передан или передан некорректный параметр";
    public Word(String word) {
        this.word = word;
    }
    public Word() {
    }
    public boolean isCorrect() {
        boolean isWord = true;
        char[] letterArray = word.toCharArray();
        for (char letter: letterArray) {
            if (!Character.isAlphabetic(letter)) {
                isWord = false;
            }
        }
        if(word.length() == 0) {
            isWord = false;
        }
        return isWord;
    }

    public int returnLength() {
        return word.length();
    }
    public String returnError() {
        return error;
    }
}
