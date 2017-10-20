CREATE TABLE fund(
fund_id INT UNSIGNED NOT NULL AUTO_INCREMENT primary key,
fund_code CHAR(6) NOT NULL UNIQUE COMMENT '基金编码',
fund_abbr VARCHAR(16) NOT NULL COMMENT '基金缩写',
fund_name VARCHAR(64) NOT NULL COMMENT '基金缩写',
fund_pinyin VARCHAR(64) NOT NULL COMMENT '基金拼音',
fund_type  VARCHAR(16) NOT NULL COMMENT '基金类型',
create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
key(fund_code),
key(fund_abbr),
key(fund_name),
key(fund_type)
)ENGINE = InnoDB
 DEFAULT CHARACTER SET = utf8
 COLLATE = utf8_general_ci;