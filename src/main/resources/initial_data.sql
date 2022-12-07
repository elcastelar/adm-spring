insert into tb_pet_type (id, i18nplaceholder, name) values (nextval('tb_pet_type_id_seq'), 'pettype.dog', 'dog');
insert into tb_pet_type (id, i18nplaceholder, name) values (nextval('tb_pet_type_id_seq'), 'pettype.cat', 'cat');

insert into tb_req_type (id, description, i18nplaceholder) values (nextval('tb_req_type_id_seq'), 'adoption term', 'adoption.term');
insert into tb_req_type (id, description, i18nplaceholder) values (nextval('tb_req_type_id_seq'), 'home pictures', 'home.pictures');

insert into tb_user (id, creationdatetime, lastlogindatetime, password, username) values (nextval('tb_user_id_seq'), '2022-12-05 22:21:22.405087', null, '123456', 'admin_test');
insert into tb_pet (id, name, creationdatetime, about, fk_owner, fk_pet_type, fk_registeredby) values (nextval('tb_pet_id_seq'), 'pet_registered', '2022-12-05 22:21:22.405087', 'my_pet_about', 1, 1, 1);

insert into tb_pet_tb_req_type (tb_pet_id, reqtypeset_id) values (1, 1);
insert into tb_pet_tb_req_type (tb_pet_id, reqtypeset_id) values (1, 2);