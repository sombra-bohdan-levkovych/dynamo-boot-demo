package com.testmonkeys.demo.utils;

public final class Constants {

    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";

    public static final Long ISSUE_REOPEN = 1L;

    public static final Long ISSUE_NEW = 2L;

    public static final Long ISSUE_IN_PROGRESS = 3L;

    public static final Long ISSUE_CONFIRMATION = 4L;

    public static final Long ISSUE_DONE = 5L;

    public static final Long ISSUE_DELETED = 6L;

    public static final Long WONT_BE_DONE = 7L;

    public static final String TYPE_PERFORMANCE_REVIEW = "PERF";

    public static final String TYPE_SOFT_REVIEW = "SOFT";

    public static final String TYPE_TECH_REVIEW = "TECHNICAL";

    public static final String ONE_TO_ONE = "one-to-one";

    public static final int PERFORMANCE_REVIEW_ID = 3;

    public static final int SOFT_REVIEW_ID = 2;

    public static final int TECH_REVIEW_ID = 1;

    public static final int ONE_TO_ONE_ID = 4;

    public static final String CLIENT_ID = "202924328008-octe35hpflkg495arbjiqsg34t2mo8f1.apps.googleusercontent.com";

    public static final String CALENDAR_ID = "primary";

    public static final String EMAIL_REGEX = "^[\\w\\-\\.\\_]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,4}$";

    public static final String CORPORATE_EMAIL_REGEX = "^[\\w\\-\\.\\_]+@sombrainc\\.com$";

    public static final String NAME_REGEX = "^[A-ZÀ-ža-zа-яА-ЯіІїЇєЄґҐйЙ\\-\\']+(\\s?[A-ZÀ-ža-zа-яА-ЯіІїЇєЄґҐйЙ\\-\\']+)*$";

    public static final String NAME_REGEX_EXTENDED = "^(?!undefined$)[A-ZÀ-ža-zа-яА-Я0-9іІїЇєЄґҐйЙ\\-\\'\\.\\,\\)\\(\\_]+(\\s?[A-ZÀ-ža-zа-яА-Я0-9іІїЇєЄґҐйЙ\\-\\'\\.\\,\\)\\(\\_]+)*$";

    public static final String PHONE_REGEX = "^(\\+38\\ ?)?(\\d{10}|\\d{3} \\d{3} \\d{2} \\d{2})$";

    public static final String SKYPE_REGEX = "^(?!undefined$)[a-z0-9\\-\\'\\.\\:\\@\\(\\_]+$";

    public static final String DESCRIPTION_REGEX = "^$|^[A-ZÀ-ža-zа-яА-Я0-9іІїЇєЄґҐйЙ%'!@#$^&*()\\-=+{}\\[\\];:|,./\\\\<>? `\"~ʼ№_\\n\\t\\r]{0,3000}$";

    public static final String UNAUTHORIZED_STATUS = "Unauthorized";

    private Constants() {
    }
}