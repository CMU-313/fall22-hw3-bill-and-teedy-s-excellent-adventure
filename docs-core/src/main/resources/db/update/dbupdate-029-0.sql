insert into T_REVIEW(REV_ID_C, REV_IDDOC_C, REV_IDUSER_C, REV_GPASCORE_C, EV_EFFORTSCORE_C, REV_EXPERIENCESCORE_C, REV_SKILLSCORE_C, REV_COMMENTS_C) values("100", "126655f7-4c8c-4fe6-908a-5a55d94f9002", "admin", "10", "10", "10", "10", "Testing comment!", NOW());
insert into T_REVIEW(REV_ID_C, REV_IDDOC_C, REV_IDUSER_C, REV_GPASCORE_C, EV_EFFORTSCORE_C, REV_EXPERIENCESCORE_C, REV_SKILLSCORE_C, REV_COMMENTS_C) values("101", "f69afff3-894b-4d9b-9ede-94d404e57c4c", "admin", "6", "6", "6", "6", "Testing comment! -- 2", NOW());

update T_CONFIG set CFG_VALUE_C = '29' where CFG_ID_C = 'DB_VERSION';