-- DB 생성
create database amado;

-- 유저 테이블 
create table user(
	userID VARCHAR(64),
	userPassword VARCHAR(64),
	userName VARCHAR(20),
	userEmail VARCHAR(50),
	PRIMARY KEY(userID)
);

-- 게시판 테이블
create table bbs(
	bbsID INT,
	bbsTitle VARCHAR(50),
	bbsContent VARCHAR(1000),
	bbsDate DATETIME,
    bbsHit INT,
	userID VARCHAR(50),
	PRIMARY KEY(bbsID)
);