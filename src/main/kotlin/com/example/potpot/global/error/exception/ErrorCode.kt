package com.example.potpot.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    UNMATCHED_PASSWORD(400, "Unmatched Password"),
    INCORRECT_PASSWORD(400, "Incorrect Password"),
    INCORRECT_USER(400, "Incorrect User"),
    NOT_ALLOW_SELF_APPLICATION(400, "Not Allow Self Application"),
    BAD_FILE_EXTENSION(400, "Bad File Extension"),

    TOKEN_INVALID(401, "Token Invalid"),
    TOKEN_EXPIRED(401, "Token Expired"),

    UNMATCHED_VERIFY_CODE(403, "Unmatched Verify Code"),
    UNEXIST_VERIFY_CODE(403, "Unexist Verify Code"),
    USER_MISMATCH_EXCEPTION(403, "User Mismatch Exception"),

    USER_NOT_FOUND(404, "User Not Found"),
    FEED_NOT_FOUND(404, "Feed Not Found"),

    ALREADY_ACCOUNT_ID(409, "Already Account Id"),
    ALREADY_EXIST_LIKE(409, "Already Exist Like"),
    ALREADY_EXIST_EMAIL(409, "Already Exist Email"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}
