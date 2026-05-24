package com.NovaStack.biblioteca.model.enums;

public enum TypeClient {
    SPECIAL ("special"),
    COMMON ("common");

    private String type;


    TypeClient(String type) {
        this.type = type;
    }

    public static TypeClient fromString(String text) {
        for (TypeClient typeClient : TypeClient.values()) {
            if (typeClient.type.equalsIgnoreCase(text)) {
                return typeClient;
            }
        }
        throw new IllegalArgumentException("type invalid");
    }
}
