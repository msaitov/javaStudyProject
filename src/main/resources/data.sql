INSERT INTO Organization (version, name, name_full, address, inn, kpp,phone,is_active) VALUES
 (0, 'Xerox','Xerox Corporation','г. Москва, ул.Цюрупы, 16', '769340695065','4829548543','+79251235464', true);
INSERT INTO Organization (version, name, name_full, address, inn, kpp,phone,is_active) VALUES
 (0,'Sony','Sony Corporation of America','Краснодар, ул. Ленина, 5', '293453623494','9384023453','+79128452354', true);
INSERT INTO Organization (version, name, name_full, address, inn, kpp,phone,is_active) VALUES
 (0,'Toshiba','Toshiba Corporation','Белгород, ул. Калашникова, 1', '830438493403','1823925443','+79438454502', false );
INSERT INTO Organization (version, name, name_full, address, inn, kpp,phone,is_active) VALUES
 (0,'Xiaomi','Xiaomi Corporation','Казань, ул. Белоусова, 20', '685495654563','3285443493','+73848328432', true );

INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 1,'Шубы','г. Москва, ул. Зеленодольская, 20', '+74954365465', true );
INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 1,'Техника','г. Москва, ул. Митчинская, 3', '+79268453453', true );
INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 2,'Стамотология','г. Краснодар, ул. Быстрова, 7', '+79295436546', true );
INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 3,'Магия Света','г. Белгород, ул. Кунцевская, 55', '+79543543543', false );
INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 4,'Газоны на заказ','г. Казань, ул. Победы, 23', '+76554543535', true );
INSERT INTO Office (version, organization_id, name, address, phone,is_active) VALUES
 (0, 4,'Пересвет','г. Казань, ул. Краснодарская, 54', '+75435454344', true );

INSERT INTO Doc_code (version, name, code) VALUES (0, 'Свидетельство о рождении',03);
INSERT INTO Doc_code (version, name, code) VALUES (0, 'Паспорт гражданина Российской Федерации',21);
INSERT INTO Doc_code (version, name, code) VALUES (0, 'Вид на жительство в Российской Федерации',12);
INSERT INTO Doc_code (version, name, code) VALUES (0, 'Паспорт иностранного гражданина',10);

INSERT INTO Citizenship (version, name, code) VALUES (0, 'Россия',643);
INSERT INTO Citizenship (version, name, code) VALUES (0, 'Великобритания',826);
INSERT INTO Citizenship (version, name, code) VALUES (0, 'Германия',276);
INSERT INTO Citizenship (version, name, code) VALUES (0, 'Франция',250);

INSERT INTO Position (version, name) VALUES (0, 'программист');
INSERT INTO Position (version, name) VALUES (0, 'бухгалтер');
INSERT INTO Position (version, name) VALUES (0, 'юрист');

INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 1, 'Василий','Петров','Кудинов','+79265854654','4565543543','1995-04-01',2,1,1,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 5, 'Максим','Иванов','Воробьев','+79295483454','5423878345','1993-02-12',1,1,2,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 6, 'Андрей','Сидоров','Смирнов','+79433424354','5436897432','1993-07-13',3,2,2,false);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 3, 'Никита','Александрович','Кузнецов','+75439458524','9634543905','1990-08-30',3,2,2,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 2, 'Антон','Генадьевич','Попов','+75438543543','9823548534','1990-12-12',1,1,3,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 4, 'Борис','Сергеевич','Новиков','+76545845493','3284243923','1990-02-11',4,4,3,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 2, 'Вадим','Матвеевич','Егоров','+75438435434','6535453654','1985-12-11',3,2,1,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 6, 'Виталий','Львович','Степанов','+78549543543','4386234146','1987-11-01',2,1,2,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 5, 'Матвей','Олегович','Белов','+78542504543','8593543965','1981-06-22',4,3,3,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 3, 'Ярослав','Тимофеевич','Кузьмин','+79438429543','9424504203','1977-02-15',2,1,1,true);
INSERT INTO Employee (version, office_id, first_name, middle_name, last_name, phone,
 doc_number, doc_date, doc_code_id, citizenship_id, position_id, is_identified) VALUES
 (0, 2, 'Леонид','Михайлович','Соколов','+79543452343','9096905435','1978-10-12',4,4,2,false);