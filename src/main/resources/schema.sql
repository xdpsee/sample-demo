CREATE TABLE IF NOT EXISTS devices (
  id BIGSERIAL PRIMARY KEY,
  gmt_create TIMESTAMP NOT NULL ,
  gmt_modified TIMESTAMP NOT NULL ,
  unique_id VARCHAR(20) NOT NULL UNIQUE ,
  name VARCHAR(64) DEFAULT NULL ,
  protocol VARCHAR(32) NOT NULL ,
  model VARCHAR(32) NOT NULL ,
  category INTEGER NOT NULL DEFAULT 1,
  phone VARCHAR(20) DEFAULT NULL ,
  contacts VARCHAR(256) DEFAULT NULL,
  extras JSONB NOT NULL
);

CREATE TABLE IF NOT EXISTS models (
  id BIGSERIAL PRIMARY KEY,
  gmt_create TIMESTAMP NOT NULL ,
  gmt_modified TIMESTAMP NOT NULL ,
  protocol VARCHAR(32) NOT NULL ,
  model VARCHAR(32) NOT NULL ,
  supported_commands VARCHAR(512) NOT NULL ,
  extras JSONB NOT NULL
) ;

CREATE UNIQUE INDEX if NOT EXISTS uk_1
ON models(protocol, model);


