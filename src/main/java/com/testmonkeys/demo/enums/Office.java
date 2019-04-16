package com.testmonkeys.demo.enums;

public enum Office {
    LVIV("Lviv"),
    IF("IF");

    private String value;

    Office(String value) {
        this.value = value;
    }

    public static Office getEnum(String type) {
        for (Office office : values()) {
            if (office.getValue().equalsIgnoreCase(type)) {
                return office;
            }
        }
        throw new IllegalArgumentException("Wrong value for Notice Type Enum");
    }

    public String getValue() {
        return value;
    }

    public Office setValue(String value) {
        this.value = value;
        return this;
    }

    public static boolean isOfficeExist(String office) {
        for (Office office1 : Office.values()) {
            if (office.equalsIgnoreCase(office1.name())) return true;
        }
        return false;
    }

}
