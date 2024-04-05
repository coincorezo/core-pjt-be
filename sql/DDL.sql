/*사용자 table */
create table user(
	user_id varchar(100) not null COMMENT '아이디',
	user_password varchar(100) not null COMMENT '비밀번호',
	email varchar(100) /*not null*/COMMENT '이메일',
	birth date  COMMENT '생일',
	gender char(5) COMMENT '성별',
	phone_number varchar(100) COMMENT '연락처',
	address varchar(255)  COMMENT '주소',
	reg_dt date   COMMENT '가입일자',
	use_yn char(5) not null  COMMENT '사용여부',
	user_level varchar(100) not null COMMENT '사용자 레벨',
	profile_picture varchar(255) COMMENT '사용자 프로필',
	 PRIMARY KEY (`user_id`)
)

/* 카테고리별 게시판 */
create table board(
	board_id int(4) not null comment '게시판 아이디',
	board_title varchar(60) not null comment '게시판 제목',
	board_content varchar(255) not null comment '내용',
	board_writer varchar(60) not null comment '작성자',
	board_view_cnt int(4) comment '조회수',
	board_update_dt date comment '수정일',
	board_reg_dt date comment '등록일',
	board_tpye varchar(60) comment '게시판 카테고리',
	use_yn char(5) comment '공개여부',
	PRIMARY KEY (`board_id`)
)