CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    name     TEXT,
    lastname TEXT,
    password TEXT,
    email    TEXT
);
CREATE TABLE question_type
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name TEXT,
    code INTEGER UNIQUE

);
CREATE TABLE polls
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        TEXT,
    start_date  TIMESTAMP WITH TIME ZONE DEFAULT now(),
    end_date    TIMESTAMP WITH TIME ZONE DEFAULT now(),
    description TEXT,
    active      BOOLEAN

);
CREATE TABLE questions
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    description TEXT,
    question_type_id BIGINT REFERENCES question_type (id) ON DELETE SET NULL,
    polls_id     BIGINT                REFERENCES polls (id) ON DELETE SET NULL

);


CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name TEXT,
    code INTEGER UNIQUE

);
CREATE TABLE link_user_role(
                               user_id  BIGINT REFERENCES users (id) ON DELETE SET NULL,
                               role_id  BIGINT REFERENCES role (id) ON DELETE SET NULL,
                               CONSTRAINT user_role_unique PRIMARY KEY(user_id,role_id)
);
CREATE TABLE token
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    token     TEXT,
    user_id   BIGINT                REFERENCES users (id) ON DELETE SET NULL,
    is_active BOOLEAN
);
CREATE TABLE user_answer
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    anon_user_id TEXT,
    questions_id BIGINT                REFERENCES questions (id) ON DELETE SET NULL,
    text_answer  text,
    time         TIMESTAMP WITH TIME ZONE DEFAULT now()
);
CREATE TABLE answer_options
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name TEXT,
    code INTEGER UNIQUE,
    questions_id BIGINT REFERENCES questions (id) ON DELETE SET NULL
);

CREATE TABLE link_user_answer_answer_options
(
    user_answer_id    BIGINT REFERENCES user_answer (id) ON DELETE SET NULL,
    answer_options_id BIGINT REFERENCES answer_options (id) ON DELETE SET NULL,

    CONSTRAINT anwer_unique PRIMARY KEY (user_answer_id, answer_options_id)
);










