drop database if exists TravelMate;
create database TravelMate;
use TravelMate;
DROP TABLE IF EXISTS user; /* 기존 테이블 삭제 */

CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
                      user_email VARCHAR(255) NOT NULL COMMENT '사용자 이메일',
                      user_name VARCHAR(255) NOT NULL COMMENT '사용자 이름',
                      password VARCHAR(255) NOT NULL COMMENT '패스워드',
                      del_yn VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '회원탈퇴여부',
                      provider VARCHAR(50) NULL COMMENT '소셜 로그인 제공자',
                      provider_id VARCHAR(255) NULL COMMENT '소셜 로그인 사용 ID',
                      regi_dtm DATE NOT NULL COMMENT '등록시간',
                      regi_id VARCHAR(255) NOT NULL COMMENT '등록유저ID',
                      updt_dtm DATE NOT NULL COMMENT '수정시간',
                      updt_id VARCHAR(255) NOT NULL COMMENT '수정유저ID'
);
