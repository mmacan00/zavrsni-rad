CREATE TABLE feature_flags.namespace
(
    namespace varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__namespac__6150EC7914C85524 PRIMARY KEY (namespace)
)

CREATE TABLE feature_flags.feature_availability_rule
(
    id          varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    active      bit                                               NOT NULL,
    description varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    feature     varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    namespace   varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__feature___3213E83F31CB6098 PRIMARY KEY (id),
    CONSTRAINT UKpp6bwavq2atoylw6ewa3l6hon UNIQUE (namespace, feature),
    CONSTRAINT FKdiyqajqbgwbeyoeiau1k54hy9 FOREIGN KEY (namespace) REFERENCES feature_flags.namespace (namespace)
)

CREATE TABLE feature_flags.or_operation
(
    id                           varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    feature_availability_rule_id varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__or_opera__3213E83F8DEDD563 PRIMARY KEY (id),
    CONSTRAINT UK8vabp1bhm43fb3kq1bnqm6l1t UNIQUE (id, feature_availability_rule_id),
    CONSTRAINT FKar51ne4h5oy89ntgbqrrc9n9h FOREIGN KEY (feature_availability_rule_id) REFERENCES feature_flags.feature_availability_rule (id)
)

CREATE TABLE FeatureFlagsDB.feature_flags.participant
(
    feature_availability_rule_id varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    user_id                      varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__particip__2D1599751D5EF598 PRIMARY KEY (feature_availability_rule_id, user_id),
    CONSTRAINT FKq6lf3cwud100al4bdxqx4s3lg FOREIGN KEY (feature_availability_rule_id) REFERENCES feature_flags.feature_availability_rule (id)
)

CREATE TABLE feature_flags.and_operation
(
    id              varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [condition]     varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    criteria        text COLLATE SQL_Latin1_General_CP1_CI_AS         NOT NULL,
    group_strategy  varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    or_operation_id varchar(36) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    CONSTRAINT PK__and_oper__3213E83F1157E5E8 PRIMARY KEY (id),
    CONSTRAINT UKnstolu2k1oyuwu2rfr6vihhsm UNIQUE (or_operation_id, group_strategy),
    CONSTRAINT FKavblce4o4gm26nhudltotvesu FOREIGN KEY (or_operation_id) REFERENCES feature_flags.or_operation (id)
)

INSERT INTO feature_flags.namespace (namespace) VALUES ('ExampleApp1'), ('ExampleApp2'), ('ExampleApp3');