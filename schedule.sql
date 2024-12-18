USE schedule;

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    author VARCHAR(100) COMMENT '작성자',
    pw VARCHAR(50) COMMENT '비밀번호',
    title VARCHAR(100) COMMENT '제목',
    contents VARCHAR(1000) COMMENT '내용',
    date DATETIME COMMENT '작성/수정일'
)