-- Используемая БД: H2 ver. 1.4.196

-- Таблица Организация
CREATE TABLE IF NOT EXISTS Organization (
  id        INTEGER              COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER     NOT NULL
  COMMENT 'Служебное поле hibernate',
  name      VARCHAR(50) NOT NULL
  COMMENT 'наименование организации',
  name_full VARCHAR(50) NOT NULL
  COMMENT 'полное название организации',
  address   VARCHAR(50) NOT NULL COMMENT 'адрес организации',
  inn       VARCHAR(30) NOT NULL COMMENT 'ИНН организации' UNIQUE ,
  kpp       VARCHAR(30) NOT NULL COMMENT 'КПП организации' UNIQUE ,
  phone     VARCHAR(30)          COMMENT 'телефон организации',
  is_active BOOLEAN COMMENT 'Статус организации: true - активен, false - не активен'
);
COMMENT ON TABLE Organization IS 'Таблица Организация';


-- Таблица Офис
CREATE TABLE IF NOT EXISTS Office (

  id              INTEGER               COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
  version         INTEGER     NOT NULL
  COMMENT 'Служебное поле hibernate',
  organization_id INTEGER     NOT NULL
  COMMENT 'нормер id из таблицы organization',
  name            VARCHAR(30) NOT NULL
  COMMENT 'наименование Офиса',
  address         VARCHAR(50) NOT NULL  COMMENT 'адрес Офиса',
  phone           VARCHAR(30)           COMMENT 'телефон Офиса',
  is_active       BOOLEAN               COMMENT 'Статус офиса: true - активен, false - не активен'
);
COMMENT ON TABLE Office IS 'Таблица Офис';



-- Таблица Работник
CREATE TABLE IF NOT EXISTS Employee (
  id             INTEGER               COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
  version        INTEGER     NOT NULL
  COMMENT 'Служебное поле hibernate',
  office_id      INTEGER     NOT NULL  COMMENT 'нормер id из таблицы Office',
  first_name     VARCHAR(50) NOT NULL  COMMENT 'имя работника',
  middle_name    VARCHAR(50)           COMMENT 'среднее, второе имя работника',
  last_name      VARCHAR(50)           COMMENT 'фамилия работника',
  phone          VARCHAR(30)           COMMENT 'телефон работника'         UNIQUE,
  doc_number     VARCHAR(50)           COMMENT 'номер документа работника' UNIQUE,
  doc_date       DATE COMMENT 'дата документа работника',
  doc_code_id    INTEGER COMMENT 'нормер id из таблицы Doc_code',
  citizenship_id INTEGER               COMMENT 'нормер id из таблицы Citizenship',
  position_id    INTEGER     NOT NULL  COMMENT 'нормер id из таблицы Position',
  is_identified  BOOLEAN               COMMENT 'Статус работника: true - идентифицируется, false - не идентифицируется'
);
COMMENT ON TABLE Employee IS 'Таблица Работник';

-- Таблица Код-документа
CREATE TABLE IF NOT EXISTS Doc_code (
    id      INTEGER               COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER      NOT NULL
    COMMENT 'Служебное поле hibernate',
    name    VARCHAR(100) NOT NULL COMMENT 'тип документа',
    code    INTEGER      NOT NULL COMMENT 'код документа' UNIQUE ,
);
COMMENT ON TABLE Doc_code IS 'Таблица Код-документа';


-- Таблица Гражданство
CREATE TABLE IF NOT EXISTS Citizenship (
    id      INTEGER               COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER      NOT NULL
    COMMENT 'Служебное поле hibernate',
    name    VARCHAR(100) NOT NULL COMMENT 'наименование гражданства',
    code    INTEGER      NOT NULL COMMENT 'код гражданства' UNIQUE,
);
COMMENT ON TABLE Citizenship IS 'Таблица Гражданство';


-- Таблица должность
CREATE TABLE IF NOT EXISTS Position (
    id      INTEGER               COMMENT 'первичный ключ с автоинкриментом' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL
    COMMENT 'Служебное поле hibernate',
    name    VARCHAR(50) NOT NULL  COMMENT 'наименование должности',
);
COMMENT ON TABLE Position IS 'Таблица должность';

-- индексы таблицы organization
CREATE INDEX IX_Organization_id         ON Organization(id);
CREATE INDEX UX_Organization_INN        ON Organization(inn);
CREATE INDEX IX_Organization_is_active  ON Organization(is_active);

-- индексы таблицы Office
CREATE INDEX IX_Office_id               ON Office (id);
CREATE INDEX IX_Office_organization_id  ON Office (organization_id);
CREATE INDEX IX_Office_name             ON Office (name);
CREATE INDEX IX_Office_phone            ON Office (phone);
CREATE INDEX IX_Office_is_active        ON Office (is_active);

-- индексы таблицы employee
CREATE INDEX IX_Employee_id             ON Employee (id);
CREATE INDEX IX_Employee_first_name     ON Employee (first_name);
CREATE INDEX IX_Employee_last_name      ON Employee (last_name);
CREATE INDEX IX_Employee_middle_name    ON Employee (middle_name);
CREATE INDEX IX_Employee_doc_code_id    ON Employee (doc_code_id);
CREATE INDEX IX_Employee_citizenship_id ON Employee (citizenship_id);
CREATE INDEX IX_Employee_position_id    ON Employee (position_id);

-- внешние ключи
ALTER TABLE Office   ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);
ALTER TABLE Employee ADD FOREIGN KEY (office_id)       REFERENCES Office(id);
ALTER TABLE Employee ADD FOREIGN KEY (doc_code_id)     REFERENCES Doc_code(id);
ALTER TABLE Employee ADD FOREIGN KEY (citizenship_id)  REFERENCES Citizenship(id);
ALTER TABLE Employee ADD FOREIGN KEY (position_id)     REFERENCES Position(id);


