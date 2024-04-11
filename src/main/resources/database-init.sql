DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS friendships CASCADE;
DROP TABLE IF EXISTS friend_requests CASCADE;
DROP TYPe IF EXISTS request_status CASCADE;

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       nickname VARCHAR(255),
                       url VARCHAR (255),
                       last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE friendships (
                             user_id_1 INT NOT NULL,
                             user_id_2 INT NOT NULL,
                             PRIMARY KEY (user_id_1, user_id_2),
                       CONSTRAINT  user_1    FOREIGN KEY (user_id_1) REFERENCES users (user_id) ON DELETE CASCADE,
                       CONSTRAINT  user_2     FOREIGN KEY (user_id_2) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE friend_requests (
                                 sender_user_id INT NOT NULL,
                                 receiver_user_id INT NOT NULL,
                                 request_status VARCHAR(255) NOT NULL,
                                 send_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (sender_user_id, receiver_user_id),
                                 CONSTRAINT fk_sender_user_id FOREIGN KEY (sender_user_id) REFERENCES users (user_id) ON DELETE CASCADE,
                                 CONSTRAINT fk_receiver_user_id FOREIGN KEY (receiver_user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
INSERT INTO users (email, password, nickname, url, last_update_time) VALUES ('nickJohn@gmail.com', 'asdqwe', 'Nick', 'https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png', '2020-06-01 00:00:00');
INSERT INTO users (email, password, nickname, url ,last_update_time) VALUES ('xiaogege@gmail.com', 'ssssss', 'Xiaogege', 'https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png', '2020-06-02 00:00:00');
INSERT INTO users (email, password, nickname, url ,last_update_time) VALUES ('xiaojiejie@gmail.com', 'asdqwe', 'Xiaojiejie', 'https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png','2020-06-03 00:00:00');
INSERT INTO users (email, password, nickname, url ,last_update_time) VALUES ('plmm@gmail.com', '123456', 'Marry','https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png', '2020-06-05 00:00:00');


INSERT INTO friendships (user_id_1, user_id_2) values (2,3);
INSERT INTO friendships (user_id_1, user_id_2) values (2,4);
INSERT INTO friendships (user_id_1, user_id_2) values (3,4);

INSERT INTO friend_requests (sender_user_id, receiver_user_id, request_status) values (1, 4, 'Pending');
