create cached table T_REVIEW (REV_ID_C varchar(36) not null, REV_IDDOC_C varchar(36) not null, REV_IDUSER_C varchar(36) not null, REV_GPASCORE_C varchar(36) not null, REV_EFFORTSCORE_C varchar(36) not null, REV_EXPERIENCESCORE_C varchar(36) not null, REV_SKILLSCORE_C varchar(36) not null, REV_COMMENTS_C varchar(4000), REV_CREATEDATE_D datetime, primary key (REV_ID_C));

alter table T_REVIEW add constraint FK_REV_IDDOC_C foreign key (REV_IDDOC_C) references T_DOCUMENT (DOC_ID_C) on delete restrict on update restrict;
alter table T_REVIEW add constraint FK_REV_IDUSER_C foreign key (REV_IDUSER_C) references T_USER (USE_ID_C) on delete restrict on update restrict;

update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';