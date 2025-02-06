SELECT USER
FROM DUAL;
--==>>SCOTT

--기존 테이블 제거
DROP TABLE TBL_BOARD PURGE;
--==>>Table TBL_BOARD이(가) 삭제되었습니다.

--실습 테이블 생성
CREATE TABLE TBL_BOARD
(NUM    NUMBER(9)           NOT NULL
,NAME   VARCHAR2(30)        NOT NULL
,PWD    VARCHAR2(20)        NOT NULL
,EMAIL  VARCHAR2(50)
,SUBJECT    VARCHAR2(100)   NOT NULL
,CONTENT    VARCHAR2(4000)  NOT NULL
,IPADDR     VARCHAR2(20)
,HITCOUNT   NUMBER          NOT NULL
,CREATED    DATE            NOT NULL
,CONSTRAINT BOARD_NUM_PK PRIMARY KEY(NUM)
);
--==>>Table TBL_BOARD이(가) 생성되었습니다

--게시판의 번호의 최댓값을 얻어내는 쿼리문

SELECT NVL(MAX(NUM),0) AS MAX
FROM TBL_BOARD;
-->>한줄 구성
SELECT NVL(MAX(NUM),0) AS MAX FROM TBL_BOARD
;
--==>0

--게시판 작성 쿼리문 구성
INSERT INTO TBL_BOARD(NUM,NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,CREATED)
VALUES(1,'공찬민','1234','kcm@test.com','작성테스트','게시물 내용 작성','211.238.142.163',0,SYSDATE);
-->한줄 구성
INSERT INTO TBL_BOARD(NUM,NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,CREATED) VALUES(1,'공찬민','1234','kcm@test.com','작성테스트','게시물 내용 작성','211.238.142.163',0,SYSDATE)
;

--확인
SELECT *
FROM TBL_BOARD;


ROLLBACK;

--DB 레코드의 갯수를 가져오는 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_BOARD;
-->한줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_BOARD
;

--특정 영역(시작번호 ~끝번호)의 게시물 목록을 읽어오는 쿼리문 구성
--번호 작성자, 제목, 조회수, 작성일

SELECT NUM,NAME,SUBJECT,HITCOUNT,CREATED
FROM
(
    SELECT ROWNUM RNUM, DATA.*
    FROM
    (
        SELECT NUM,NAME,SUBJECT,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED
        FROM TBL_BOARD
        ORDER BY NUM DESC
    )DATA
)
WHERE RNUM>=1 AND RNUM<=10;
--한 줄 구성
SELECT NUM,NAME,SUBJECT,HITCOUNT,CREATED FROM (SELECT ROWNUM RNUM, DATA.* FROM ( SELECT NUM,NAME,SUBJECT,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED FROM TBL_BOARD ORDER BY NUM DESC)DATA)WHERE RNUM>=1 AND RNUM<=10
;


--특정 게시물 조회에 따른 조회 횟수 증가 쿼리문 구성
UPDATE TBL_BOARD
SET HITCOUNT = HITCOUNT +1
WHERE NUM = 1;
-->한 줄 구성
UPDATE TBL_BOARD SET HITCOUNT = HITCOUNT +1 WHERE NUM = 1
;

--실무적 → 로그화

--특정 게시물의 내용을 읽어오는 쿼리문 구성
SELECT NUM, NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED
FROM TBL_BOARD
WHERE NUM =1;
-->한줄 구성
SELECT NUM, NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED FROM TBL_BOARD  WHERE NUM =1
;


--특정 게시물을 삭제하는 쿼리문
DELETE
FROM TBL_BOARD
WHERE NUM = 1;
-->한 줄 구성
DELETE FROM TBL_BOARD WHERE NUM = 2
;

COMMIT;

--특정 게시물을 수정하는 쿼리문
--(게시물 상세보기 페이지 → Article.jsp 내에서의 처리)
--수정 가능 항목 : 작성자, 패스워드, 이메일,제목, 내용

UPDATE TBL_BOARD
SET NAME='곽유진',PWD='9987',EMAIL='kyj@test.com',SUBJECT ='수정한 제목',CONTENT='수정한 내용'
WHERE NUM=1;
-->한 줄 구성
UPDATE TBL_BOARD SET NAME='곽유진',PWD='9987',EMAIL='kyj@test.com',SUBJECT ='수정한 제목',CONTENT='수정한 내용'WHERE NUM=1
;

--확인
SELECT *
FROM TBL_BOARD;


--커밋
COMMIT;

--특정 게시물의 다음 번호 읽어오는 쿼리문 구성
SELECT NUM
FROM 
(
    SELECT ROWNUM RNUM, DATA.*
    FROM
    (
        SELECT NUM
        FROM TBL_BOARD
    )DATA
)
WHERE RNUM = 
(   SELECT RNUM
    FROM 
    (
        SELECT ROWNUM RNUM, DATA.*
        FROM
        (
            SELECT NUM
            FROM TBL_BOARD
        )DATA
    )
    WHERE NUM =1
)+1;


SELECT NVL(MIN(NUM),-1) NEXTNUM 
FROM TBL_BOARD
WHERE NUM>50;
-->한 줄 구성

SELECT NVL(MIN(NUM),-1) NEXTNUM  FROM TBL_BOARD WHERE NUM>50
;

--특정 게시물(50)의 이전 번호 읽어오는 쿼리문 작성
SELECT MAX(NUM)
FROM TBL_BOARD
WHERE NUM<50
;

--------------------------------------------------------------------------------
---실습 진행간 테스트 및 처리
SELECT *
FROM TBL_BOARD;

--커밋
COMMIT;
--==>>커밋완료

SELECT * 
FROM TBL_BOARD;
--==>>데이터 입력 확인

--커밋
COMMIT;
--==>>커밋완료