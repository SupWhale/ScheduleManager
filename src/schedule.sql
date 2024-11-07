//테이블 생성
CREATE TABLE `Schedule` (
                            `scheduleId`	VARCHAR2	NOT NULL,
                            `userId`	VARCHAR2	NOT NULL,
                            `title`	VARCHAR2	NULL,
                            `content`	VARCHAR2	NULL,
                            `ManagePW`	VARCHAR2	NULL,
                            `scheduleStDate`	DATE	NULL,
                            `scheduleEdDate`	DATE	NULL
);

CREATE TABLE `User` (
                        `userId`	VARCHAR2	NOT NULL,
                        `userName`	VARCHAR2	NULL
);

ALTER TABLE `Schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
                                                                 `scheduleId`
    );

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                                         `userId`
    );




//일정 상세 조회하기
select
         scheduleId
        , userId
        , title
        , content
        , scheduleStDate
        , scheduleEdDate
from Schedule
where scheduleId = "" and userId = "";

//전체 일정 목록 조회하기
select
       scheduleId
     , userId
     , title
     , content
     , scheduleStDate
     , scheduleEdDate
from Schedule;

//일정 생성하기
INSERT into Schedule
    (   scheduleId
     , userId
     , title
     , content
     , scheduleStDate
     , scheduleEdDate)
value ("","","","","","");

//일정 삭제하기
delete Schedule
where scheduleId = "" and userId = "";

//일정 수정하기
update Schedule
Set
      title = "오늘의 일정",
      content = "오늘의 일정입니다.",
      managePW = "qwer123",
      scheduleStDate = "2024-10-31",
      scheduleEdDate = "2024-10-31"
where scheduleId = "sc2024103103" and userId = "kim123",

