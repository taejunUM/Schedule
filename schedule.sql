USE schedule;

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    author VARCHAR(100) COMMENT '작성자',
    title VARCHAR(100) COMMENT '제목',
    contents VARCHAR(1000) COMMENT '내용',
    createDate DATETIME COMMENT '작성일',
    updateDate DATETIME COMMENT '수정일'
);

CREATE TABLE user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '유저 식별자',
    userName VARCHAR(20) COMMENT '유저명',
    email VARCHAR(100) COMMENT '이메일',
    pw VARCHAR(100) COMMENT '비밀번호',
    createDate DATETIME COMMENT '작성일'
);