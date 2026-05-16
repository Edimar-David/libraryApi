package com.NovaStack.biblioteca.model.enums;

public enum BookCategory {
    FICTION  ("fiction"),
    ROMANCE  ("romance"),
    ACTION   ("action"),
    ACADEMIC ("academic");


    private String category;


    BookCategory(String category) {
        this.category = category;
    }

    public static BookCategory fromString(String text) {
        for (BookCategory categoria : BookCategory.values()) {
            if (categoria.category.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("type invalid");
    }
}
