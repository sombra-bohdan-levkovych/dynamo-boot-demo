package com.testmonkeys.demo.utils;

public enum RoleType {

    ROLE_USER("ROLE_USER"),
    ROLE_HR("ROLE_HR"),
    ROLE_RECRUITER("ROLE_RECRUITER"),
    ROLE_IMPLEMENTER_TECHNICAL("ROLE_IMPLEMENTER_TECHNICAL"),
    ROLE_IMPLEMENTER_OFFICE("ROLE_IMPLEMENTER_OFFICE"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PM("ROLE_PM"),
    ROLE_FINANCE_MANAGER("ROLE_FINANCE_MANAGER"),
    ROLE_TOP_MANAGER("ROLE_TOP_MANAGER"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
    ROLE_SALES("ROLE_SALES"),
    ROLE_SALES_ADDITIONAL("ROLE_SALES_ADDITIONAL"),
    ROLE_RECRUITMENT_COORDINATOR("ROLE_RECRUITMENT_COORDINATOR"),
    ROLE_SOMBRAMONEY_MANAGER("ROLE_SOMBRAMONEY_MANAGER"),
    ROLE_CONSULTANT("ROLE_CONSULTANT"),
    ROLE_DIGEST_MANAGER("ROLE_DIGEST_MANAGER"),
    ROLE_NEWS_MANAGER("ROLE_NEWS_MANAGER"),
    ROLE_PROJECTS_VIEWER("ROLE_PROJECTS_VIEWER"),
    ROLE_DEV_1C("ROLE_DEV_1C"),
    ROLE_EVENT_MANAGER("ROLE_EVENT_MANAGER");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public static RoleType getEnum(String value) {
        for (RoleType role : values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Wrong value for Role Enum");
    }

    public String getValue() {
        return this.roleName;
    }
}
