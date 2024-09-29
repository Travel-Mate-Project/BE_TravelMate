INSERT INTO user (user_email, user_name, password, provider, provider_id, regi_dtm, regi_id, updt_dtm, updt_id)
VALUES ('example@example.com', '홍길동', 'password123', 'google', '1234567890', '2024-09-11', 1, '2024-09-11', 1);


insert into course (user_id, name, start_date, end_date, place_list, total_time, display_yn, save_yn, del_yn, regi_id)
VALUES (1, -- user_id
        '테스트 여행코스 1', -- name
        '2024-09-27', -- start_date
        '2024-09-28', -- end_date
        '[1,2]', -- place_list (optional)
        40, -- total_time (hours)
        'Y',
        'Y', -- save_yn (Y for final save)
        'N', -- del_yn (N for not deleted)
        'sudong' -- regi_id (the ID of the user who registered the course)
       );

insert into course (user_id, name, start_date, end_date, place_list, total_time, display_yn, save_yn, del_yn, regi_id)
VALUES (2, -- user_id
        '테스트 여행 코스 2', -- name
        '2024-10-01', -- start_date
        '2024-10-31', -- end_date
        '[1,2,3,4]', -- place_list (optional)
        140, -- total_time (hours)
        'Y',
        'Y', -- save_yn (Y for final save)
        'N', -- del_yn (N for not deleted)
        'sudong'
       );