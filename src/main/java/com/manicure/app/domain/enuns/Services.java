package com.manicure.app.domain.enuns;

public enum Services {
    TRADITIONAL_MANICURE("Traditional_Manicure"),
    HAND_SPA("Hand_Spa"),
    MANICURE_GEL_POLISH("Manicure_and_Gel_Polish"),

    TRADITIONAL_PEDICURE("Traditional_Pedicure"),
    PEDICURE_FOOT_SPA("Pedicure_and_Foot_Spa"),
    PEDICURE_GEL_POLISH("Pedicure_and_Gel_Polish"),

    GEL_EXTENSION("Gel_Extension"),
    FIBER_EXTENSION("Fiber_Extension"),
    EXTENSION_MAINTENANCE("Extension_Maintenance"),

    // Nail Art
    SIMPLE_DETAIL("Simple_Detail"),
    FULL_NAIL_ART("Full_Nail_Art"),
    FRENCH_MANICURE("French_Manicure"),

    DEEP_CUTICLE_REMOVAL("Deep_Cuticle_Removal"),
    KERATIN_STRENGTHENING("Keratin_Strengthening"),
    NAIL_SHIELDING("Nail_Shielding");

    private final String service;

    Services(String service) {
        this.service = service;
    }
}
