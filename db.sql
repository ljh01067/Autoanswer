DROP DATABASE IF EXISTS autoanswer;
CREATE DATABASE autoanswer;
USE autoanswer;

CREATE TABLE auto(
                     id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     question TEXT NOT NULL,
                     answer TEXT NOT NULL
);

INSERT INTO auto
SET
    question = '로그인',
answer = '상단의 로그인 버튼을 클릭한 후 로그인 정보를 입력하세요';

INSERT INTO auto
SET
    question = '회원가입',
answer = '로그인 페이지에서 회원가입 버튼을 클릭한 후 회원 정보를 입력하세요';

INSERT INTO auto
SET
    question = '내 정보',
answer = '상단의 프로필을 클릭하시면 뜨는 팝업에 있는 내 정보 버튼을 누르시면 됩니다';

INSERT INTO auto
SET
    question = '계정삭제',
answer = '내 정보 페이지에서 계정삭제 버튼을 클릭하시면 됩니다';

INSERT INTO auto
SET
    question = '게시글',
answer = '게시글 사이트에 들어가시면 게시글 작성과 수정, 삭제를 할 수 있습니다';