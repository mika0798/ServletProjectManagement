CREATE SCHEMA IF NOT EXISTS crm;
USE crm;

CREATE TABLE IF NOT EXISTS role (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS user (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL, 
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(36),
    address VARCHAR(36),
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS status (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS project (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    start_date DATE,
    end_date DATE,
    created_by INT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE IF NOT EXISTS task (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    start_date DATE,
    end_date DATE,
    assignee INT NOT NULL,
    project_id INT NOT NULL,
    status_id INT NOT NULL,
    FOREIGN KEY (assignee) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (status_id) REFERENCES status (id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO role (name,description) VALUE ('ADMIN','1');
INSERT INTO role (name,description) VALUE ('LEADER','2');
INSERT INTO role (name,description) VALUE ('MEMBER','3');

INSERT INTO status(name,description) VALUE('Not Started','Not yet started the job');
INSERT INTO status(name,description) VALUE('In Progress','On going');
INSERT INTO status(name,description) VALUE('Completed','Job done');

INSERT INTO user(email,name,password,phone,address,role_id) 
VALUE('admin@email.com','Admin','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','123456','Saigon',1);

INSERT INTO user(email,name,password,phone,address,role_id)
VALUE('leader1@email.com','Leader 1','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','123456','Hanoi',2);

INSERT INTO user(email,name,password,phone,address,role_id)
VALUE('leader2@email.com','Leader 2','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','123456','Hanoi',2);

INSERT INTO user(email,name,password,phone,address,role_id)
VALUE('member@email.com','Member','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','123456','Africa',3);

INSERT INTO user(email,name,password,phone,address,role_id) 
VALUE('brad@email.com','Brad Magnus','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','1236549870','United Kingdom',3);

INSERT INTO user(email,name,password,phone,address,role_id) 
VALUE('jack@email.com','Jack Carlson','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','1234526406','United States',3);

INSERT INTO user(email,name,password,phone,address,role_id) 
VALUE('talia@email.com','Talia Young','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','1237810456','Australia',3);

INSERT INTO user(email,name,password,phone,address,role_id) 
VALUE('khang@email.com','Khang Nguyen','$2a$12$APj1jFYqHikSClV/mXEcDumQ9eAUoqY10A/6yUJrghFCbTfWxKW1O','8926123456','Canada',3);

INSERT INTO project(name, description, start_date, end_date, created_by)
VALUE('Project Management', 'Simple CRUD Practice','2025-10-28','2025-12-20',2);

INSERT INTO project(name, description, start_date, end_date, created_by)
VALUE('Hotel Booking App', 'Airbnb Clone','2025-10-29','2025-12-30',3);


INSERT INTO task(name,description,start_date,end_date,assignee,project_id,status_id)
VALUE('Frontend','React','2025-10-28','2025-10-29',4,1,2);

INSERT INTO task(name,description,start_date,end_date,assignee,project_id,status_id)
VALUE('Backend','Servlet','2025-10-28','2025-10-29',5,1,2);

INSERT INTO task(name,description,start_date,end_date,assignee,project_id,status_id)
VALUE('Frontend','Angular','2025-10-29','2025-11-10',6,2,1);

INSERT INTO task(name,description,start_date,end_date,assignee,project_id,status_id)
VALUE('Database','MongoDB, MySQL','2025-10-30','2025-11-15',7,2,2);

INSERT INTO task(name,description,start_date,end_date,assignee,project_id,status_id)
VALUE('Backend','Spring Boot','2025-10-30','2025-12-01',8,2,2);





    