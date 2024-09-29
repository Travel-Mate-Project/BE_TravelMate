drop database if exists TravelMate;
create database TravelMate;
use TravelMate;
DROP TABLE IF EXISTS user;

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


DROP TABLE IF EXISTS course; /* 기존 테이블 삭제 */

CREATE TABLE course (
                        id INT AUTO_INCREMENT PRIMARY KEY COMMENT '코스 ID',
                        user_id INT NOT NULL COMMENT '유저 ID',
                        name VARCHAR(255) NOT NULL COMMENT '코스 이름',
                        start_date DATE NOT NULL COMMENT '시작일',
                        end_date DATE NOT NULL COMMENT '종료일',
                        place_list VARCHAR(255) NULL COMMENT '장소 list',
                        total_time INT NULL DEFAULT 0 COMMENT '총 소요시간',
                        display_yn VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '노출 여부',
                        save_yn VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT 'N: 임시저장, Y: 최종저장',
                        del_yn VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제유무',
                        regi_dtm DATETIME NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '등록시간',
                        regi_id VARCHAR(255) NULL COMMENT '등록유저ID',
                        updt_dtm DATE NULL COMMENT '수정시간',
                        updt_id VARCHAR(255) NULL COMMENT '수정유저ID'
);