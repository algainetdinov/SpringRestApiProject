CREATE TABLE IF NOT EXISTS country (
    id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    code	   VARCHAR(3) NOT NULL,
    name       VARCHAR(64)  NOT NULL
);

CREATE TABLE IF NOT EXISTS doctype (
    id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    code	   VARCHAR(2) NOT NULL,
    name       VARCHAR(128)  NOT NULL
);

CREATE TABLE IF NOT EXISTS document (
    id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    date	   DATE DEFAULT NULL,
    number     VARCHAR(32) DEFAULT NULL,
    type_code  BIGINT DEFAULT NULL,
	FOREIGN KEY (type_code) REFERENCES doctype(id)
);

 CREATE TABLE IF NOT EXISTS organization (
	id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
	name	   VARCHAR(64) NOT NULL,
	full_name  VARCHAR(64) NOT NULL,
	inn		   VARCHAR(10) NOT NULL,
	kpp		   VARCHAR(9) NOT NULL,
	address    VARCHAR(64) NOT NULL,
	phone      VARCHAR(18) DEFAULT NULL,
	is_active  BOOLEAN DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS office (
	id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
	name	   VARCHAR(64) NOT NULL,
	address    VARCHAR(64) NOT NULL,
	phone      VARCHAR(18) DEFAULT NULL,
	org_id	   BIGINT DEFAULT NULL,
	is_active  BOOLEAN DEFAULT NULL,
	FOREIGN KEY (org_id) REFERENCES organization(id)
);

CREATE TABLE IF NOT EXISTS user (
	id            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER NOT NULL,
	first_name    VARCHAR(32) NOT NULL,
	last_name     VARCHAR(32) DEFAULT NULL,
	middle_name   VARCHAR(32) DEFAULT NULL,
	is_identified BOOLEAN DEFAULT NULL,
	position 	  VARCHAR(32) NOT NULL,
	phone      	  VARCHAR(18) DEFAULT NULL,
	citiz_id	  BIGINT DEFAULT NULL,
	doc_id	      BIGINT DEFAULT NULL,
	office_id	  BIGINT DEFAULT NULL,
	FOREIGN KEY (citiz_id) REFERENCES country(id),
	FOREIGN KEY (doc_id) REFERENCES document(id),
	FOREIGN KEY (office_id) REFERENCES office(id)
); 

CREATE INDEX IX_office_name ON office (name);
CREATE INDEX IX_office_phone ON office (phone);
CREATE INDEX IX_office_is_active ON office (is_active);

CREATE INDEX IX_user_first_name ON user (first_name);
CREATE INDEX IX_user_last_name ON user (last_name);
CREATE INDEX IX_user_middle_name ON user (middle_name);
CREATE INDEX IX_user_position ON user (position);

CREATE INDEX IX_organization_name ON organization (name);
CREATE INDEX IX_organization_inn ON organization (inn);
CREATE INDEX IX_organization_is_active ON organization (is_active);

CREATE INDEX IX_doctype_code on doctype (code);

CREATE INDEX IX_country_code on country (code);