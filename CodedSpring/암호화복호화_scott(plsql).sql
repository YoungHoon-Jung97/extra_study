--(현재 SCOTT 으로 연결된 상태)

-- ○ 패키지 선언(CRYPTPACK)
CREATE OR REPLACE PACKAGE CRYPTPACK
AS
    -- 암호화 함수 패키지 구성
    FUNCTION ENCRYPT(STR VARCHAR2, HASH VARCHAR2)
    RETURN VARCHAR2;
    
    -- 복호화 함수 패키지 구성
    FUNCTION DECRYPT(XCRYPT VARCHAR2, HASH VARCHAR2)
    RETURN VARCHAR2;

END CRYPTPACK;
--==>> Package CRYPTPACK이(가) 컴파일되었습니다.

-- ○ 패키지 몸체 구성(CRYPTPACK)
CREATE OR REPLACE PACKAGE BODY CRYPTPACK
IS
    -- 전역 변수 구성
    CRYPTED_STRING VARCHAR2(2000);

    -- 암호화 함수 정의
    FUNCTION ENCRYPT(STR VARCHAR2, HASH VARCHAR2)
    RETURN VARCHAR2
    IS
        PIECES_OF_EIGHT NUMBER := ((FLOOR(LENGTH(STR)/8 + .9)) *8);
    BEGIN
        DBMS_OBFUSCATION_TOOLKIT.DESENCRYPT
        ( INPUT_STRING     => RPAD(STR, PIECES_OF_EIGHT)
        , KEY_STRING       => RPAD(HASH, 8, '#')
        , ENCRYPTED_STRING => CRYPTED_STRING
        );
        RETURN CRYPTED_STRING;
    END;
    
    -- 복호화 함수 정의
    FUNCTION DECRYPT(XCRYPT VARCHAR2, HASH VARCHAR2)
    RETURN VARCHAR2
    IS
    BEGIN
        DBMS_OBFUSCATION_TOOLKIT.DESDECRYPT
        ( INPUT_STRING        => XCRYPT
        , KEY_STRING          => RPAD(HASH, 8, '#')
        , DECRYPTED_STRING    => CRYPTED_STRING
        );
        RETURN TRIM(CRYPTED_STRING);
    END;

END CRYPTPACK;
--==>> Package Body CRYPTPACK이(가) 컴파일되었습니다.