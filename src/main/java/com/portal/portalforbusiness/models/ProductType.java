package com.portal.portalforbusiness.models;

public enum ProductType {
    IT("IT"),
    FOODSTUFF("Foodstuff"),
    CLOTHES("Clothes"),
    PERFUMERY("Perfumery");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
