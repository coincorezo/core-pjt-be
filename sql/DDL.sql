/*사용자 table */
create table user
(
    user_id         varchar(100) not null comment '아이디',
    user_password   varchar(100) not null comment '비밀번호',
    email           varchar(100) /*not null*/comment '이메일',
    birth           date comment '생일',
    gender          char(5) comment '성별',
    phone_number    varchar(100) comment '연락처',
    address         varchar(255) comment '주소',
    reg_dt          date comment '가입일자',
    use_yn          char(5)      not null comment '사용여부',
    user_level      varchar(100) not null comment '사용자 레벨',
    profile_picture varchar(255) comment '사용자 프로필',
    primary key (user_id)
);

/* 카테고리별 게시판 */
create table board
(
    board_id        int(4)       not null comment '게시판 아이디',
    board_title     varchar(60)  not null comment '게시판 제목',
    board_content   varchar(255) not null comment '내용',
    board_writer    varchar(60)  not null comment '작성자',
    board_view_cnt  int(4) comment '조회수',
    board_update_dt date comment '수정일',
    board_reg_dt    date comment '등록일',
    board_type      varchar(60) comment '게시판 카테고리',
    use_yn          char(5) comment '공개여부',
    primary key (board_id)
);

/* 공통코드 */
create table common_code
(
    common_code             varchar(60) not null comment '공통코드',
    common_code_name        varchar(60) comment '공통코드명',
    common_code_description varchar(255) comment '공통코드설명',
    use_yn                  char(1) comment '사용여부',
    reg_dt                  date comment '등록일자',
    reg_id                  varchar(100) comment '등록자',
    upt_dt                  date comment '수정일자',
    upt_id                  varchar(100) comment '수정자',
    primary key (common_code)
);

/* 그룹코드 */
create table group_code
(
    common_code            varchar(60) not null comment '공통코드',
    group_code             varchar(60) not null comment '그룹코드',
    group_code_name        varchar(60) comment '그룹코드명',
    group_code_description varchar(255) comment '그룹코드설명',
    use_yn                 char(1) comment '사용여부',
    reg_dt                 date comment '등록일자',
    reg_id                 varchar(100) comment '등록자',
    upt_dt                 date comment '수정일자',
    upt_id                 varchar(100) comment '수정자'
);
