-- Fill doctype table

INSERT INTO doctype (id, version, code, name) VALUES (1, 0, '03', 'Свидетельство о рождении');

INSERT INTO doctype (id, version, code, name) VALUES (2, 0, '07', 'Военный билет');

INSERT INTO doctype (id, version, code, name) VALUES (3, 0, '08', 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO doctype (id, version, code, name) VALUES (4, 0, '10', 'Паспорт иностранного гражданина');

INSERT INTO doctype (id, version, code, name) VALUES (5, 0, '11', 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу');

INSERT INTO doctype (id, version, code, name) VALUES (6, 0, '12', 'Вид на жительство в Российской Федерации');

INSERT INTO doctype (id, version, code, name) VALUES (7, 0, '13', 'Удостоверение беженца');

INSERT INTO doctype (id, version, code, name) VALUES (8, 0, '15', 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO doctype (id, version, code, name) VALUES (9, 0, '18', 'Свидетельство о предоставлении временного убежища на территории Российской Федерации');

INSERT INTO doctype (id, version, code, name) VALUES (10, 0, '21', 'Паспорт гражданина Российской Федерации');

INSERT INTO doctype (id, version, code, name) VALUES (11, 0, '23', 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');

INSERT INTO doctype (id, version, code, name) VALUES (12, 0, '24', 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO doctype (id, version, code, name) VALUES (13, 0, '91', 'Иные документы');

-- Fill country table

INSERT INTO country (id, version, code, name) VALUES (1, 0, '643', 'Российская федерация');

INSERT INTO country (id, version, code, name) VALUES (2, 0, '156', 'Китай');

INSERT INTO country (id, version, code, name) VALUES (3, 0, '036', 'Австралия');

INSERT INTO country (id, version, code, name) VALUES (4, 0, '276', 'Германия');

INSERT INTO country (id, version, code, name) VALUES (5, 0, '840', 'Соединенные Штаты Америки');

-- Fill organization table

INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (1, 0,'ПАО "Газпром"', 'Публичное акционерное общество "Газпром"', 
'7736050003', '772801001', '117420, г. Москва, ул. Намёткина, д. 16', '+8 (495) 719-23-68', true);
				
INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (2, 0,'ООО "Макдоналдс"', 'Общество с ограниченной ответственностью "Макдоналдс"', 
'7710044140', '770501001', '121002, г. Москва, пер. Большой Николопесковский, д. 15, стр. 2', '+8 (495) 755-66-41', true);

INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (3, 0,'АО "Белл Интегратор"', 'Акционерное общество "Белл Интегратор"', 
'7714923230', '771401001', '125167, г. Москва, ул. Планетная , д. 11, пом. 9/10 РМ-4', '+8 (495) 755-66-41', true);

INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (4, 0,'ПАО "Мегафон"', 'Публичное акционерное общество "Мегафон"', 
'7812014560', '770701001', '115035, г. Москва, Кадашевская наб., д. 30', '+8 (495) 730-28-82', false);

INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (5, 0,'АО "Альфа-банк"', 'Акционерное общество "Альфа-банк"', 
'7728168971', '775001001', '107078, г. Москва, ул. Маши Порываевой, д. 11', '+8 (495) 788-88-78', false);

-- Fill document table

INSERT INTO document (id, version, date, number, type_code) VALUES (1, 0, '1969-04-21', '8010 345432', 10);

INSERT INTO document (id, version, date, number, type_code) VALUES (2, 0, '1965-03-11', '7645 665472', 10);

INSERT INTO document (id, version, date, number, type_code) VALUES (3, 0, '1999-12-14', 'II-ЕР №547832', 1);

INSERT INTO document (id, version, date, number, type_code) VALUES (4, 0, '1976-11-03', 'АС №5643542', 3);

INSERT INTO document (id, version, date, number, type_code) VALUES (5, 0, '2012-05-09', '90 6654572', 6);

-- Fill office table

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (1, 0, 'Альфа-Банк офис «Агидель»', 'Уфа, ул. Ленина, д. 32/1', '+7 (372) 232-33-22', 5, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (2, 0, 'Альфа-Банк офис «Простор»', 'Уфа, ул. Жукова, д. 29', '+7 (372) 232-22-22', 5, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (3, 0, 'Альфа-Банк офис «Уфа-Проспект Октября»', 'Уфа, пр-т Октября, д. 6', '+7 (372) 211-22-22', 5, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (4, 0, 'Макдоналдс Мубарякова', 'Уфа, ул. Мубарякова, д. 1', '+7 (347) 222-20-03', 2, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (5, 0, 'Макдоналдс Ленина', 'Уфа, ул. Ленина, д. 5', '+7 (347) 222-11-03', 2, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (6, 0, 'Белл Интегратор Уфа', 'Уфа, ул. Свердлова, д. 92', '+7 (495) 980-61-82', 3, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (7, 0, 'Мегафон Ленина', 'Уфа, ул. Ленина, д. 26', '+7 (347) 267-26-03', 4, true);

INSERT INTO office (id, version, name, address, phone, org_id, is_active) VALUES (8, 0, 'Мегафон Рыльского', 'Уфа, ул. Рыльского, д. 23', '+7 (347) 253-63-03', 4, true);

-- Fill user table

INSERT INTO user (id, version, first_name, last_name, middle_name, is_identified, position, phone, citiz_id, doc_id, office_id) VALUES (1, 0, 'Иван', 'Иванов', 'Константинович', true, 'Кассир', '+7-(900)-000-00-00', 1, 1, 1);

INSERT INTO user (id, version, first_name, last_name, middle_name, is_identified, position, phone, citiz_id, doc_id, office_id) VALUES (2, 0, 'Кси', 'Пинг', 'Федорович', true, 'Начальник отдела', '+7-(900)-000-00-01', 2, 2, 2);

INSERT INTO user (id, version, first_name, last_name, middle_name, is_identified, position, phone, citiz_id, doc_id, office_id) VALUES (3, 0, 'Джон', 'Адамс', 'Сергеевич', true, 'Менеджер', '+7-(900)-000-00-02', 3, 3, 3);

INSERT INTO user (id, version, first_name, last_name, middle_name, is_identified, position, phone, citiz_id, doc_id, office_id) VALUES (4, 0, 'Иозеф', 'Шмидт', 'Александрович', true, 'Управляющий залом', '+7-(900)-000-00-03', 4, 4, 4);

INSERT INTO user (id, version, first_name, last_name, middle_name, is_identified, position, phone, citiz_id, doc_id, office_id) VALUES (5, 0, 'Дональд', 'Сандерс', 'Иванович', true, 'Управляющий залом', '+7-(900)-000-00-04', 5, 5, 5);

