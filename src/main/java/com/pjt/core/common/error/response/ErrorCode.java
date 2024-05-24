package com.pjt.core.common.error.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // common
    SUCCESS("common", "성공"),
    ERROR("common", "에러"),
    NOT_SAVE("common", "저장 되지않았습니다"),
    NO_DATA("common", "데이터가 존재하지 않습니다."),
    INVALID_INPUT_VALUE("common", "유효하지 않는 입력 값입니다."),
    INTERNAL_SERVER_ERROR("common", "서버 에러가 발생하였습니다."),
    NOT_FOUND_CATEGORY("common", "해당 카테고리가 존재하지 않습니다."),

    // file
    CREATE_DIRECTORY_ERROR("file", "파일 디렉토리 생성 중 에러가 발생하였습니다."),
    FILE_NOT_EXIST("file", "파일 정보가 존재하지 않습니다."),
    SAVE_FILE_ERROR("file", "파일 저장 중 에러가 발생하였습니다."),
    INVALID_FILE("file", "존재하지 않는 파일이거나 유효한 파일이 아닙니다."),
    INVALID_DIRECTORY("file", "유효하지 않는 파일 경로입니다."),
    INVALID_IMAGE_TYPE("file", "유효하지 않는 이미지 타입입니다."),

    // member
    NO_MEMBER("member", "존재하지 않는 회원입니다."),
    INVALID_PASSWORD("member", "비밀번호가 일치하지 않습니다."),
    EXIST_MEMBER("member", "이미 존재하는 회원입니다."),
    NO_TOKEN("member", "토큰이 존재하지 않습니다."),
    FAIL_CREATE_MEMBER("member", "회원 가입에 실패하였습니다."),
    UNAUTHORIZED("member", "접근 권한이 없습니다. 로그인 후 다시 시도해 주세요."),
    ACCESS_DENIED("member", "필요한 권한이 없거나 접근이 제한되어 있습니다."),
    NOT_EQUALS_USER("member", "일치하지 않는 회원입니다."),

    //coin
    NO_COIN("member", "코인이 없습니다");


    private final String code;
    private final String message;
}
