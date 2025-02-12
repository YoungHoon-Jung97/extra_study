--■■■ 암호화 복호화 확인실습 ■■■--

CREATE TABLE TBL_EXAM
( ID    NUMBER
, PW    VARCHAR2(20)
);
--==>> Table TBL_EXAM이(가) 생성되었습니다.

--○ 데이터 입력(비 암호화)
INSERT INTO TBL_EXAM(ID,PW) VALUES(1,'abcd1234');
--==>> 1 행 이(가) 삽입되었습니다.

--○ 확인
SELECT *
FROM TBL_EXAM;
--==>> 1	abcd1234

--○ 롤백
ROLLBACK;
--==>> 롤백 완료.

--○ 기존 레코드 제거
DELETE
FROM TBL_EXAM;
--==>> 1 행 이(가) 삭제되었습니다.

--○ 커밋
COMMIT;
--==>> 커밋 완료.

--○ 다시 데이터 입력(암호화)
INSERT INTO TBL_EXAM(ID,PW) VALUES(1, CRYPTPACK.ENCRYPT('abcd1234','0626'));
--==>> 1 행 이(가) 삽입되었습니다.

--○ 확인
SELECT *
FROM TBL_EXAM;
--==>> 1	??V

--○ 데이터 조회(복호화)
SELECT ID, CRYPTPACK.DECRYPT(PW,'1111') AS PW
FROM TBL_EXAM;
--==>> 1	?3??

SELECT ID, CRYPTPACK.DECRYPT(PW,'1234') AS PW
FROM TBL_EXAM;
--==>> 1	???

SELECT ID, CRYPTPACK.DECRYPT(PW,'0626') AS PW
FROM TBL_EXAM;
--==>> 1	abcd1234