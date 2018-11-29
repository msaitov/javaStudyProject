-- Используемая БД: H2 ver. 1.4.196

-- Таблица Организация
CREATE TABLE IF NOT EXISTS Organization (
    -- id первичный ключ таблицы Организации с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- имя организации
    name                VARCHAR(50) NOT NULL,

    -- полное имя организации
    name_full           VARCHAR(50) NOT NULL,

    -- адрес организации
    address             VARCHAR(50) NOT NULL,

    -- ИНН организации
    inn                 VARCHAR(30) UNIQUE NOT NULL,

    -- КПП организации
    kpp                 VARCHAR(30) UNIQUE NOT NULL,

    -- Телефон организации
    phone               VARCHAR(30),

    -- Статус организации: true - активна, false - не активна
    is_active           BOOLEAN
);


-- Таблица Офис
CREATE TABLE IF NOT EXISTS Office (

    -- id первичный ключ таблицы Офис с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- нормер id из таблицы Organization
    organization_id     INTEGER NOT NULL,

    -- Имя офиса
    name                VARCHAR(30) NOT NULL,

    -- адрес Офиса
    address             VARCHAR(50) NOT NULL,

    -- телефон Офиса
    phone               VARCHAR(30),

    -- Статус офиса: true - активен, false - не активен
    is_active           BOOLEAN
);


-- Таблица Работник
CREATE TABLE IF NOT EXISTS Employee (

    -- id первичный ключ таблицы Работник с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- нормер id из таблицы Office
    office_id           INTEGER NOT NULL,

    -- Имя работника
    first_name          VARCHAR(50) NOT NULL,

    -- среднее, второе имя работника
    middle_name         VARCHAR(50),

    -- фамилия работника
    last_name           VARCHAR(50),

    -- телефон работника
    phone               VARCHAR(30) UNIQUE,

    -- номер документа работника
    doc_number          VARCHAR(50) UNIQUE,

    -- дата документа работника
    doc_date            DATE,

    -- нормер id из таблицы Doc_code
    doc_code_id         INTEGER,

    -- нормер id из таблицы Citizenship
    citizenship_id      INTEGER,

    -- нормер id из таблицы Position
    position_id         INTEGER NOT NULL,

    -- Статус работника: true - идентифицируется, false - не идентифицируется
    is_identified       BOOLEAN
);


-- Таблица Код-документа
CREATE TABLE IF NOT EXISTS Doc_code (

    -- id первичный ключ таблицы Код-документа с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- тип документа
    name                VARCHAR(100) NOT NULL,

    -- код документа
    code                INTEGER UNIQUE NOT NULL,
);

-- Таблица Гражданство
CREATE TABLE IF NOT EXISTS Citizenship (

    -- id первичный ключ таблицы Гражданство с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- наименование гражданства
    name                VARCHAR(100) NOT NULL,

    -- код гражданства
    code                INTEGER UNIQUE NOT NULL,
);


-- Таблица должность
CREATE TABLE IF NOT EXISTS Position (

    -- id первичный ключ таблицы Код-документа с автоинкриментом
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,

    -- наименование должности
    name                VARCHAR(50) NOT NULL,
);

-- индексы таблицы Organization
CREATE INDEX IX_Organization_id         ON Organization(id);
CREATE INDEX UX_Organization_INN        ON Organization(inn);
CREATE INDEX IX_Organization_is_active  ON Organization(is_active);

-- индексы таблицы Office
CREATE INDEX IX_Office_id               ON Office (id);
CREATE INDEX IX_Office_organization_id  ON Office (organization_id);
CREATE INDEX IX_Office_name             ON Office (name);
CREATE INDEX IX_Office_phone            ON Office (phone);
CREATE INDEX IX_Office_is_active        ON Office (is_active);

-- индексы таблицы Employee
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

