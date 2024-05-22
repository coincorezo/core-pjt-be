DROP TABLE IF EXISTS `board`;

CREATE TABLE `board`
(
    `board_id`       int(4)       NOT NULL COMMENT '게시판 아이디',
    `board_title`    varchar(60)  NOT NULL COMMENT '게시판 제목',
    `board_content`  varchar(225) NOT NULL COMMENT '내용',
    `board_writer`   varchar(60)  NOT NULL COMMENT '작성자',
    `board_view_cnt` int(4)       NULL COMMENT '조회수',
    `category`       varchar(60)  NOT NULL COMMENT '게시글 카테고리',
    `board_status`   varchar(10)  NOT NULL COMMENT '게시글 상태값(비공개/공개/삭제)',
    `reg_dt`         timestamp    NOT NULL COMMENT '등록일',
    `upt_dt`         timestamp    NOT NULL COMMENT '수정일'
);

DROP TABLE IF EXISTS `points_history`;

CREATE TABLE `points_history`
(
    `history_id`    bigint       NOT NULL COMMENT '히스토리ID',
    `user_id`       varchar(100) NOT NULL COMMENT '아이디',
    `points_change` int          NOT NULL COMMENT '변경포인트',
    `points_amount` int          NOT NULL COMMENT '최종포인트',
    `reason`        varchar(100) NOT NULL COMMENT '변경사유',
    `reg_dt`        timestamp    NULL COMMENT '사용일자'
);

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply`
(
    `reply_id`      varchar(255) NOT NULL COMMENT '댓글아이디',
    `board_id`      int(4)       NOT NULL COMMENT '게시판 아이디',
    `reply_comment` varchar(200) NOT NULL COMMENT '댓글내용',
    `reg_dt`        timestamp    NOT NULL COMMENT '등록날짜',
    `update_dt`     timestamp    NOT NULL COMMENT '수정날짜',
    `use_yn`        char(2)      NULL COMMENT '삭제여부'
);

DROP TABLE IF EXISTS `emoticon`;

CREATE TABLE `emoticon`
(
    `emoticon_id`    int          NOT NULL COMMENT '이모티콘 ID',
    `emoticon_title` varchar(255) NULL COMMENT '이모티콘제목',
    `emoticon_price` int          NULL COMMENT '이모티콘가격',
    `reg_dt`         timestamp    NOT NULL COMMENT '등록일자',
    `reg_id`         varchar(100) NOT NULL COMMENT '등록자',
    `upd_dt`         timestamp    NOT NULL COMMENT '수정일자',
    `upd_id`         varchar(100) NOT NULL COMMENT '수정자'
);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `user_id`         varchar(100) NOT NULL COMMENT '아이디',
    `user_password`   varchar(100) NOT NULL COMMENT '비밀번호',
    `email`           varchar(100) NULL COMMENT '이메일',
    `birth`           timestamp    NULL COMMENT '생일',
    `gender`          char(5)      NULL COMMENT '성별',
    `phone_number`    varchar(100) NULL COMMENT '연락처',
    `address`         varchar(255) NULL COMMENT '주소',
    `reg_dt`          timestamp    NOT NULL COMMENT '가입일자',
    `use_yn`          char(5)      NOT NULL COMMENT '사용여부',
    `user_level`      varchar(100) NOT NULL COMMENT '사용자레밸',
    `profile_picture` varchar(255) NULL COMMENT '사용자프로필'
);

DROP TABLE IF EXISTS `emoticon_detail`;

CREATE TABLE `emoticon_detail`
(
    `emoticon_detail_id` varchar(100) NOT NULL COMMENT '이모티콘상세ID',
    `emoticon_id`        int          NOT NULL COMMENT '이모티콘 ID',
    `emoticon_nm`        varchar(255) NULL COMMENT '이모티콘명',
    `reg_dt`             timestamp    NOT NULL COMMENT '등록일자',
    `reg_id`             varchar(100) NOT NULL COMMENT '등록자'
);

DROP TABLE IF EXISTS `profile_img`;

CREATE TABLE `profile_img`
(
    `img_no`        int          NOT NULL COMMENT '이미지번호',
    `user_id`       varchar(100) NOT NULL COMMENT '아이디',
    `img_nm`        varchar(225) NOT NULL COMMENT '이미지이름',
    `img_ext_nm`    varchar(100) NOT NULL COMMENT '이미지 확장자',
    `img_file_size` varchar(100) NOT NULL COMMENT '파일사이즈',
    `file_url_path` varchar(225) NULL COMMENT '이미지 물리경로'
);

DROP TABLE IF EXISTS `emoticon_img`;

CREATE TABLE `emoticon_img`
(
    `emoticon_img_no`    int          NOT NULL COMMENT '이미지번호',
    `emoticon_detail_id` varchar(100) NOT NULL COMMENT '이모티콘상세ID',
    `emoticon_img_nm`    varchar(225) NOT NULL COMMENT '이미지이름',
    `img_ext_nm`         varchar(100) NOT NULL COMMENT '이미지 확장자',
    `img_file_size`      varchar(100) NOT NULL COMMENT '파일사이즈',
    `file_url_path`      varchar(225) NULL COMMENT '이미지 물리경로'
);

DROP TABLE IF EXISTS `login_history`;

CREATE TABLE `login_history`
(
    `user_id`  varchar(100) NOT NULL COMMENT '아이디',
    `login_dt` timestamp    NULL COMMENT '로그인시간',
    `login_ip` varchar(100) NULL COMMENT 'ip'
);

DROP TABLE IF EXISTS `user_emoticon`;

CREATE TABLE `user_emoticon`
(
    `emoticon_id` int          NOT NULL COMMENT '이모티콘 ID',
    `user_id`     varchar(100) NOT NULL COMMENT '아이디',
    `reg_dt`      timestamp    NULL COMMENT '등록일시'
);

DROP TABLE IF EXISTS `emoticon_history`;

CREATE TABLE `emoticon_history`
(
    `history_id`     bigint       NOT NULL COMMENT '히스토리ID',
    `user_id`        varchar(100) NOT NULL COMMENT '아이디',
    `emoticon_id`    int          NOT NULL COMMENT '이모티콘ID',
    `emoticon_price` int          NULL COMMENT '이모티콘 가격',
    `reg_dt`         timestamp    NULL COMMENT '구입일자'
);

DROP TABLE IF EXISTS `favorite`;

CREATE TABLE `favorite`
(
    `emoticon_id` int          NOT NULL COMMENT '이모티콘 ID',
    `user_id`     varchar(100) NOT NULL COMMENT '사용자 ID',
    `reg_dt`      timestamp    NOT NULL COMMENT '등록일'
);

DROP TABLE IF EXISTS `common_code`;

CREATE TABLE `common_code`
(
    `common_code`             varchar(60)  NOT NULL COMMENT '공통코드',
    `common_code_name`        varchar(60)  NULL COMMENT '공통코드명',
    `common_code_description` varchar(255) NULL COMMENT '공통코드설명',
    `ref`                     varchar(60)  NULL COMMENT '참조코드',
    `depth`                   int          NULL COMMENT '계층',
    `use_yn`                  char(1)      NULL COMMENT '사용여부',
    `reg_dt`                  date         NULL COMMENT '등록일',
    `reg_id`                  varchar(100) NULL COMMENT '등록자',
    `upt_dt`                  date         NULL COMMENT '수정일',
    `upt_id`                  varchar(100) NULL COMMENT '수정자'
);

DROP TABLE IF EXISTS `board_img`;

CREATE TABLE `board_img`
(
    `img_no`          int          NOT NULL COMMENT '이미지번호',
    `board_id`        int(4)       NOT NULL COMMENT '게시판 아이디',
    `emoticon_img_nm` varchar(225) NOT NULL COMMENT '이미지이름',
    `img_ext_nm`      varchar(100) NOT NULL COMMENT '이미지 확장자',
    `img_file_size`   varchar(100) NOT NULL COMMENT '파일사이즈',
    `file_url_path`   varchar(225) NULL COMMENT '이미지 물리경로'
);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (`board_id`);

ALTER TABLE `points_history` ADD CONSTRAINT `PK_POINTS_HISTORY` PRIMARY KEY (`history_id`);

ALTER TABLE `reply` ADD CONSTRAINT `PK_REPLY` PRIMARY KEY (`reply_id`);

ALTER TABLE `emoticon` ADD CONSTRAINT `PK_EMOTICON` PRIMARY KEY (`emoticon_id`);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (`user_id`);

ALTER TABLE `emoticon_detail` ADD CONSTRAINT `PK_EMOTICON_DETAIL` PRIMARY KEY (`emoticon_detail_id`);

ALTER TABLE `profile_img` ADD CONSTRAINT `PK_PROFILE_IMG` PRIMARY KEY (`img_no`);

ALTER TABLE `emoticon_img` ADD CONSTRAINT `PK_EMOTICON_IMG` PRIMARY KEY (`emoticon_img_no`);

ALTER TABLE `emoticon_history` ADD CONSTRAINT `PK_EMOTICON_HISTORY` PRIMARY KEY (`history_id`);

ALTER TABLE `common_code` ADD CONSTRAINT `PK_COMMON_CODE` PRIMARY KEY (`common_code`);

ALTER TABLE `board_img` ADD CONSTRAINT `PK_BOARD_IMG` PRIMARY KEY (`img_no`);

alter table board modify board_id int not null auto_increment;
alter table board_img modify img_no int not null auto_increment;
alter table profile_img modify img_no int not null auto_increment;
alter table emoticon_img modify emoticon_img_no int not null auto_increment;
alter table reply modify reply_id int not null auto_increment;
alter table points_history modify history_id bigint not null auto_increment;
alter table emoticon_history modify history_id bigint not null auto_increment;
alter table emoticon modify emoticon_id int not null auto_increment;