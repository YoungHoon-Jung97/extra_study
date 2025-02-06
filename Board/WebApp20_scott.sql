SELECT USER
FROM DUAL;
--==>>SCOTT

--���� ���̺� ����
DROP TABLE TBL_BOARD PURGE;
--==>>Table TBL_BOARD��(��) �����Ǿ����ϴ�.

--�ǽ� ���̺� ����
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
--==>>Table TBL_BOARD��(��) �����Ǿ����ϴ�

--�Խ����� ��ȣ�� �ִ��� ���� ������

SELECT NVL(MAX(NUM),0) AS MAX
FROM TBL_BOARD;
-->>���� ����
SELECT NVL(MAX(NUM),0) AS MAX FROM TBL_BOARD
;
--==>0

--�Խ��� �ۼ� ������ ����
INSERT INTO TBL_BOARD(NUM,NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,CREATED)
VALUES(1,'������','1234','kcm@test.com','�ۼ��׽�Ʈ','�Խù� ���� �ۼ�','211.238.142.163',0,SYSDATE);
-->���� ����
INSERT INTO TBL_BOARD(NUM,NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,CREATED) VALUES(1,'������','1234','kcm@test.com','�ۼ��׽�Ʈ','�Խù� ���� �ۼ�','211.238.142.163',0,SYSDATE)
;

--Ȯ��
SELECT *
FROM TBL_BOARD;


ROLLBACK;

--DB ���ڵ��� ������ �������� ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_BOARD;
-->���� ����
SELECT COUNT(*) AS COUNT FROM TBL_BOARD
;

--Ư�� ����(���۹�ȣ ~����ȣ)�� �Խù� ����� �о���� ������ ����
--��ȣ �ۼ���, ����, ��ȸ��, �ۼ���

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
--�� �� ����
SELECT NUM,NAME,SUBJECT,HITCOUNT,CREATED FROM (SELECT ROWNUM RNUM, DATA.* FROM ( SELECT NUM,NAME,SUBJECT,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED FROM TBL_BOARD ORDER BY NUM DESC)DATA)WHERE RNUM>=1 AND RNUM<=10
;


--Ư�� �Խù� ��ȸ�� ���� ��ȸ Ƚ�� ���� ������ ����
UPDATE TBL_BOARD
SET HITCOUNT = HITCOUNT +1
WHERE NUM = 1;
-->�� �� ����
UPDATE TBL_BOARD SET HITCOUNT = HITCOUNT +1 WHERE NUM = 1
;

--�ǹ��� �� �α�ȭ

--Ư�� �Խù��� ������ �о���� ������ ����
SELECT NUM, NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED
FROM TBL_BOARD
WHERE NUM =1;
-->���� ����
SELECT NUM, NAME,PWD,EMAIL,SUBJECT,CONTENT,IPADDR,HITCOUNT,TO_CHAR(CREATED,'YYYY-MM-DD') AS CREATED FROM TBL_BOARD  WHERE NUM =1
;


--Ư�� �Խù��� �����ϴ� ������
DELETE
FROM TBL_BOARD
WHERE NUM = 1;
-->�� �� ����
DELETE FROM TBL_BOARD WHERE NUM = 2
;

COMMIT;

--Ư�� �Խù��� �����ϴ� ������
--(�Խù� �󼼺��� ������ �� Article.jsp �������� ó��)
--���� ���� �׸� : �ۼ���, �н�����, �̸���,����, ����

UPDATE TBL_BOARD
SET NAME='������',PWD='9987',EMAIL='kyj@test.com',SUBJECT ='������ ����',CONTENT='������ ����'
WHERE NUM=1;
-->�� �� ����
UPDATE TBL_BOARD SET NAME='������',PWD='9987',EMAIL='kyj@test.com',SUBJECT ='������ ����',CONTENT='������ ����'WHERE NUM=1
;

--Ȯ��
SELECT *
FROM TBL_BOARD;


--Ŀ��
COMMIT;

--Ư�� �Խù��� ���� ��ȣ �о���� ������ ����
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
-->�� �� ����

SELECT NVL(MIN(NUM),-1) NEXTNUM  FROM TBL_BOARD WHERE NUM>50
;

--Ư�� �Խù�(50)�� ���� ��ȣ �о���� ������ �ۼ�
SELECT MAX(NUM)
FROM TBL_BOARD
WHERE NUM<50
;

--------------------------------------------------------------------------------
---�ǽ� ���ణ �׽�Ʈ �� ó��
SELECT *
FROM TBL_BOARD;

--Ŀ��
COMMIT;
--==>>Ŀ�ԿϷ�

SELECT * 
FROM TBL_BOARD;
--==>>������ �Է� Ȯ��

--Ŀ��
COMMIT;
--==>>Ŀ�ԿϷ�