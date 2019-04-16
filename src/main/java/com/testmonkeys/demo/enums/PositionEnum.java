package com.testmonkeys.demo.enums;

public enum PositionEnum {

    CEO("CEO"),
    CTO("CTO"),
    CSO("CSO"),
    HEAD_OF_DEPARTMENT("Head of Department"),

    ACCOUNT_ASSISTANT("Accountant Assistant"),
    ACCOUNTANT("Accountant"),
    CHIEF_ACCOUNTANT("Chief Accountant"),
    ACCOUNT_ASSISTANT_LLC("Accountant Assistant LLC"),
    ACCOUNTANT_LLC("Accountant LLC"),
    CHIEF_ACCOUNTANT_LLC("Chief Accountant LLC"),
    ACCOUNT_ASSISTANT_PE("Accountant Assistant PE"),
    ACCOUNTANT_PE("Accountant PE"),
    CHIEF_ACCOUNTANT_PE("Chief Accountant PE"),
    CONTROLLER("Controller"),

    ENGAGEMENT_MANAGER("Engagement manager"),
    HR_MANAGER("HR Manager"),
    HR_MARKETING("HR marketing specialist"),
    EVENT_MANAGER("Event Manager"),
    OFFICE_ADMIN("Office Administrator"),
    HEAD_OF_OFFICE_ADMIN("Head of Office Administration"),
    OFFICE_MANAGER("Office Manager"),
    OFFICE_SUPPORT_SPEC("Office Support Specialist"),
    TRAINEE_RECRUITER("Trainee Recruiter"),
    JUN_RECRUITER("Junior Recruiter"),
    RECRUITER("Recruiter"),
    SALES_ASSISTANT("Sales assistant"),
    SALES_ASSISTANT_CALLS("Sales assistant (Cold calls)"),
    HEAD_OF_SYS_ADMIN("Head of System Administration"),
    SYS_ADMIN("System Administrator"),
    JUN_ADMIN_ASSISTANT("Junior System Administrator"),
    UX_UI("UX/UI Designer"),
    TRAINEE_UX_UI("Trainee UX/UI Designer"),
    JUN_UX_UI("Junior UX/UI Designer"),
    STRONG_JUN_UX_UI("Strong Junior UX/UI Designer"),
    MID_UX_UI("Middle UX/UI Designer"),
    STRONG_MID_UX_UI("Strong Middle UX/UI Designer"),
    SEN_UX_UI("Senior UX/UI Designer"),
    JUN_PM("Junior Project Manager"),
    PM("Project Manager"),
    TRAINEE_PROJECT_COORDINATOR("Trainee Project Coordinator"),
    JUN_PROJECT_COORDINATOR("Junior Project Coordinator"),
    PROJECT_COORDINATOR("Project Coordinator"),
    RECRUITMENT_COORDINATOR("Recruitment Coordinator"),
    HR_COORDINATOR("HR Coordinator"),
    HEAD_OF_FINANCE_AND_LEGAL("Head of Finance and Legal"),
    DELIVERY_DIRECTOR("Delivery Director"),
    IF_OFFICE_COORDINATOR("IF Office Coordinator"),
    CONSULTANT("Consultant"),
    LEAD_GENERATION_SPEC("Lead Generation Specialist"),
    ENGLISH_TEACHER("English teacher"),
    CONTENT_MARKETING_SPEC("Content marketing specialist"),

    TRAINEE_FRONTED_DEV("Trainee Front-end developer"),
    TRAINEE_JAVA_DEV("Trainee Java developer"),
    JUN_FRONTEND_DEV("Junior Front-end developer"),
    JUN_JAVA_DEV("Junior Java developer"),
    STRONG_JUN_FRONTEND_DEV("Strong Junior Front-end developer"),
    STRONG_JUN_JAVA_DEV("Strong Junior Java developer"),
    MID_JAVA_DEV("Middle Java developer"),
    MID_FRONTEND_DEV("Middle Front-end developer"),
    STRONG_MID_JAVA_DEV("Strong Middle Java developer"),
    STRONG_MID_FRONTEND_DEV("Strong Middle Front-end developer"),
    SEN_JAVA_DEV("Senior Java developer"),
    SEN_FRONTEND_DEV("Senior Front-end developer"),

    TRAINEE_QA_MANUAL("Trainee QA Manual"),
    TRAINEE_QA_AUTOMATION("Trainee QA Automation"),
    JUN_QA_MANUAL("Junior QA Manual"),
    JUN_QA_AUTOMATION("Junior QA Automation"),
    STRONG_JUN_QA_MANUAL("Strong Junior QA Manual"),
    STRONG_JUN_QA_AUTOMATION("Strong Junior QA Automation"),
    MID_QA_MANUAL("Middle QA Manual"),
    MID_QA_AUTOMATION("Middle QA Automation"),
    STRONG_MID_QA_MANUAL("Strong Middle QA Manual"),
    STRONG_MID_QA_AUTOMATION("Strong Middle QA Automation"),
    SEN_QA_MANUAL("Senior QA Manual"),
    SEN_QA_AUTOMATION("Senior QA Automation"),
    WORD_PRESS_DEV("WordPress developer"),
    DEV_1C("1C developer");

    private final String position;

    PositionEnum(String position) {
        this.position = position;
    }

    public static PositionEnum findByPosition(String position) {
        for (PositionEnum positionEnum : values()) {
            if (positionEnum.getValue().equals(position)) {
                return positionEnum;
            }
        }
        throw new IllegalArgumentException("Wrong value for position Enum - " + position);
    }

    public static boolean checkByPosition(String position) {
        for (PositionEnum positionEnum : values()) {
            if (positionEnum.getValue().equals(position))
                return true;
        }
        return false;
    }

    public String getValue() {
        return position;
    }
}