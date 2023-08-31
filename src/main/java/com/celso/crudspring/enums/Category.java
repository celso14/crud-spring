package com.celso.crudspring.enums;

public enum Category {
    
    BACKEND("Back-End"), FRONTEND("Front-End");

    private String value;

    private Category(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
