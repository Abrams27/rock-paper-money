CREATE TABLE IF NOT EXISTS USER_INFO (
    username                TEXT        NOT NULL    PRIMARY KEY,
    password                TEXT        NOT NULL,
    balance                 INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS GAME_HISTORY (
    id                      SERIAL      NOT NULL    PRIMARY KEY,
    opponent_username       TEXT        NOT NULL,
    game_result             TEXT        NOT NULL,
    stake                   INTEGER     NOT NULL,

    user_username           TEXT        NOT NULL,

    CONSTRAINT user_fk FOREIGN KEY (user_username)
        REFERENCES USER_INFO (username)
);
