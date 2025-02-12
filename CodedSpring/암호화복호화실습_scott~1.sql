--���� ��ȣȭ ��ȣȭ Ȯ�νǽ� ����--

CREATE TABLE TBL_EXAM
( ID    NUMBER
, PW    VARCHAR2(20)
);
--==>> Table TBL_EXAM��(��) �����Ǿ����ϴ�.

--�� ������ �Է�(�� ��ȣȭ)
INSERT INTO TBL_EXAM(ID,PW) VALUES(1,'abcd1234');
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ��
SELECT *
FROM TBL_EXAM;
--==>> 1	abcd1234

--�� �ѹ�
ROLLBACK;
--==>> �ѹ� �Ϸ�.

--�� ���� ���ڵ� ����
DELETE
FROM TBL_EXAM;
--==>> 1 �� ��(��) �����Ǿ����ϴ�.

--�� Ŀ��
COMMIT;
--==>> Ŀ�� �Ϸ�.

--�� �ٽ� ������ �Է�(��ȣȭ)
INSERT INTO TBL_EXAM(ID,PW) VALUES(1, CRYPTPACK.ENCRYPT('abcd1234','0626'));
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ��
SELECT *
FROM TBL_EXAM;
--==>> 1	??V

--�� ������ ��ȸ(��ȣȭ)
SELECT ID, CRYPTPACK.DECRYPT(PW,'1111') AS PW
FROM TBL_EXAM;
--==>> 1	?3??

SELECT ID, CRYPTPACK.DECRYPT(PW,'1234') AS PW
FROM TBL_EXAM;
--==>> 1	???

SELECT ID, CRYPTPACK.DECRYPT(PW,'0626') AS PW
FROM TBL_EXAM;
--==>> 1	abcd1234