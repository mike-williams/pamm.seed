# Users schema

# --- !Ups

CREATE TABLE user (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  email              VARCHAR(255) NOT NULL UNIQUE,
  forename           VARCHAR(50),
  surname            VARCHAR(50),
  password           VARCHAR(255),
  job_title          VARCHAR(20),
  base_site          VARCHAR(20),
  phone              VARCHAR(20),
  activated          BOOLEAN      NOT NULL DEFAULT FALSE,
  activation_key     VARCHAR(255) NOT NULL,
  activation_date    DATE,
  failed_login_count INTEGER      NOT NULL DEFAULT 0,
  last_access        DATETIME     NOT NULL DEFAULT SYSDATE(),
  role               VARCHAR(10)  NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT ck_user_role CHECK (role IN ('ADMIN', 'USER'))
);

CREATE TABLE project (
  id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  title         VARCHAR(255) NOT NULL,
  project_code  VARCHAR(20),
  client        VARCHAR(255),
  summary       VARCHAR(255) NOT NULL,
  sessionStatus VARCHAR(50),
  owner_user_id BIGINT(20)   NOT NULL,
  status        VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (owner_user_id) REFERENCES user (id)
);

CREATE TABLE project_user (
  project_id BIGINT(20)   NOT NULL,
  user_email VARCHAR(255) NOT NULL,
  user_id    BIGINT(20),
  forename   VARCHAR(50),
  surname    VARCHAR(50),
  role       VARCHAR(20),
  PRIMARY KEY (project_id, user_email),
  FOREIGN KEY (project_id) REFERENCES project (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE meeting (
  id             BIGINT(20)   NOT NULL AUTO_INCREMENT,
  subject        VARCHAR(255) NOT NULL,
  summary        VARCHAR(255) NOT NULL,
  scheduled_date DATETIME     NOT NULL,
  review_by_date DATETIME,
  project_id     BIGINT(20)   NOT NULL,
  owner_user_id  BIGINT(20)   NOT NULL,
  status         VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (owner_user_id) REFERENCES user (id),
  FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE meeting_user (
  id         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  meeting_id BIGINT(20),
  user_email VARCHAR(255) NOT NULL,
  user_id    BIGINT(20),
  forename   VARCHAR(50),
  surname    VARCHAR(50),
  role       VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (meeting_id) REFERENCES meeting (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE comment (
  id                BIGINT(20)    NOT NULL AUTO_INCREMENT,
  reference         VARCHAR(30),
  text              VARCHAR(1024) NOT NULL,
  entry_date        DATETIME      NOT NULL DEFAULT SYSDATE(),
  meeting_id        BIGINT(20)    NOT NULL,
  parent_comment_id BIGINT(20),
  meeting_user_id   BIGINT(20)    NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (meeting_user_id) REFERENCES meeting_user (id),
  FOREIGN KEY (meeting_id) REFERENCES meeting (id)
);

CREATE TABLE issue (
  id         BIGINT(20)    NOT NULL AUTO_INCREMENT,
  text       VARCHAR(1024) NOT NULL,
  entry_date DATETIME      NOT NULL DEFAULT SYSDATE(),
  user_id    BIGINT(20)    NOT NULL,
  status     VARCHAR(20)   NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES project_user (user_id)
);

# --- !Downs

DROP TABLE comment;
DROP TABLE meeting_user;
DROP TABLE meeting;
DROP TABLE issue;
DROP TABLE project_user;
DROP TABLE project;
DROP TABLE user;
