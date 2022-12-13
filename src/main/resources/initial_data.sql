insert into tb_user_role (id, name) values (nextval('tb_user_role_id_seq'), 'admin');
insert into tb_user_role (id, name) values (nextval('tb_user_role_id_seq'), 'user');
insert into tb_user_role (id, name) values (nextval('tb_user_role_id_seq'), 'system');

insert into tb_user (id, creationdatetime, lastlogindatetime, password, username) values (nextval('tb_user_id_seq'), null, null, '{noop}123456', 'admin');
insert into tb_user (id, creationdatetime, lastlogindatetime, password, username) values (nextval('tb_user_id_seq'), null, null, '{noop}123456', 'user');
insert into tb_user (id, creationdatetime, lastlogindatetime, password, username) values (nextval('tb_user_id_seq'), null, null, '{noop}123456', 'petcare');

insert into tb_user_to_role (tb_user_id, userroles_id) values (1, 1);
insert into tb_user_to_role (tb_user_id, userroles_id) values (2, 2);
insert into tb_user_to_role (tb_user_id, userroles_id) values (3, 3);