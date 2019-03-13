package com.example.lokeshkumar.splash;

/**
 * Created by lokesh kumar on 10/25/2017.
 */

public class KeyValue {
    private String key = "";
    private String value = "";

    public KeyValue(String key, String value)
    {
        this.key = key;
        this.value =value;
    }

    public KeyValue()
    {

    }

    public String getKey() {
        return key;
    }

    public void setX(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

