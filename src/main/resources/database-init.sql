DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS friendships CASCADE;
DROP TABLE IF EXISTS friend_requests CASCADE;
DROP TYPe IF EXISTS request_status CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       nickname VARCHAR(255),
                       last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE friendships (
                             user_id_1 INT NOT NULL,
                             user_id_2 INT NOT NULL,
                             PRIMARY KEY (user_id_1, user_id_2),
                             CONSTRAINT  user_1    FOREIGN KEY (user_id_1) REFERENCES users (user_id) ON DELETE CASCADE,
                             CONSTRAINT  user_2     FOREIGN KEY (user_id_2) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TYPE request_status AS ENUM ('Pending', 'Accepted', 'Rejected', 'Expired');
CREATE TABLE friend_requests (
                                 sender_user_id INT NOT NULL,
                                 receiver_user_id INT NOT NULL,
                                 request_status request_status NOT NULL,
                                 send_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (sender_user_id, receiver_user_id),
                                 CONSTRAINT fk_sender_user_id FOREIGN KEY (sender_user_id) REFERENCES users (user_id) ON DELETE CASCADE,
                                 CONSTRAINT fk_receiver_user_id FOREIGN KEY (receiver_user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
INSERT INTO users (email, password, nickname, last_update_time) VALUES ('nickJohn@gmail.com', 'asdqwe', 'Nick', '2020-06-01 00:00:00');
INSERT INTO users (email, password, nickname, last_update_time) VALUES ('xiaogege@gmail.com', 'ssssss', 'Xiaogege', '2020-06-02 00:00:00');
INSERT INTO users (email, password, nickname, last_update_time) VALUES ('xiaojiejie@gmail.com', 'asdqwe', 'Xiaojiejie', '2020-06-03 00:00:00');
INSERT INTO users (email, password, nickname, last_update_time) VALUES ('plmm@gmail.com', '123456', 'Marry', '2020-06-05 00:00:00');



INSERT INTO friendships (user_id_1, user_id_2) values (2,3);
INSERT INTO friendships (user_id_1, user_id_2) values (2,4);
INSERT INTO friendships (user_id_1, user_id_2) values (3,4);

CREATE TABLE messages (
                          message_id SERIAL PRIMARY KEY,
                          sender_user_id INT NOT NULL,
                          receiver_user_id INT NOT NULL,
                          CONSTRAINT  user_1    FOREIGN KEY (sender_user_id) REFERENCES users (user_id) ON DELETE CASCADE,
                          CONSTRAINT  user_2    FOREIGN KEY (receiver_user_id) REFERENCES users (user_id) ON DELETE CASCADE,
                          text TEXT,
                          is_read BOOLEAN DEFAULT false,
                          send_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO messages (sender_user_id, receiver_user_id, text) values (1, 2, 'hello, how are you?');
INSERT INTO messages (sender_user_id, receiver_user_id, text) values (2, 1, 'I am fine, thank you!');
INSERT INTO messages (sender_user_id, receiver_user_id, text) values (1, 2, 'Awesome!');

CREATE TABLE rooms (
                          room_id SERIAL PRIMARY KEY,
                          user_id_1 INT NOT NULL,
                          user_id_2 INT NOT NULL,
                          CONSTRAINT  user_1    FOREIGN KEY (user_id_1) REFERENCES users (user_id) ON DELETE CASCADE,
                          CONSTRAINT  user_2     FOREIGN KEY (user_id_2) REFERENCES users (user_id) ON DELETE CASCADE,
                          create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);