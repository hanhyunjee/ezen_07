Drop table USER_t;


create table USER_t(
    userID VARCHAR2(20),
    userPassword VARCHAR2(10),
    userName VARCHAR2(10),
    userGender VARCHAR2(10),
    userEmail VARCHAR2(20),
    primary key(userid)
);
insert into USER_t values('kkan','1234','깐쇼','남','kkan@naver.com');
insert into USER_t values('dong','1234','동원','남','dong@naver.com');
insert into USER_t values('spring','1234','봄이','여','sping@naver.com');
commit;

SELECT
    *
FROM USER_t;










DROP TABLE t_Board CASCADE CONSTRAINTS;

create table t_Board(
    articleNO number(10) primary key,
    parentNO number(10) default 0,
    title varchar2(500) not null,
    content varchar2(4000),
    imageFileName varchar2(30),
    writedate date default sysdate not null,
    id varchar2(10),
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES t_member(id)
);

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(1, 0, '질문있어요', '미국여행 어떤가요.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(2, 0, '질문있어요.', '보라카이 가보고싶어요', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(3, 2, '답변입니다.', '여행 후기에 대한 내용입니다.', null, sysdate, 'dong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(5, 3, '답변입니다.', '여행 좋습니다.', null, sysdate, 'lee');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(4, 0, '가입인사', '김유신 가입인사드려요', null, sysdate, 'kim');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(6, 2, '답변입니다', '반가워요!!!', null, sysdate, 'lee');

commit;

SELECT
    *
FROM t_board;


SELECT LEVEL,
       articleNO,
       parentNO,
       LPAD(' ', 4*(LEVEL-1)) || title title,
       content,
       writeDate,
       id
       FROM t_board
       START WITH parentNO=0
       CONNECT BY PRIOR articleNO=parentNO
       ORDER SIBLINGS BY articleNO DESC;
       



