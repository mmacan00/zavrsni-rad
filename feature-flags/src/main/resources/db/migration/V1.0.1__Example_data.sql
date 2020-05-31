INSERT INTO feature_flags.namespace (namespace) VALUES ('Service A'), ('Service B'), ('Service C');

INSERT INTO FeatureFlagsDB.feature_flags.feature_availability_rule
(id, active, description, feature, namespace)
VALUES('85d8028f-d0aa-4422-8420-0dd4afb23a95', 1, 'desc', 'Feature A', 'Service A');
INSERT INTO FeatureFlagsDB.feature_flags.feature_availability_rule
(id, active, description, feature, namespace)
VALUES('8901771a-659f-4287-a4cd-69c32303ebe5', 1, 'desc', 'Feature B', 'Service B');

INSERT INTO FeatureFlagsDB.feature_flags.or_operation
(id, feature_availability_rule_id)
VALUES('9a597dcb-e0f3-4114-a603-54605dd910ab', '8901771a-659f-4287-a4cd-69c32303ebe5');
INSERT INTO FeatureFlagsDB.feature_flags.or_operation
(id, feature_availability_rule_id)
VALUES('a450d3cf-e7b7-4e53-bb57-0425460e2207', '85d8028f-d0aa-4422-8420-0dd4afb23a95');
INSERT INTO FeatureFlagsDB.feature_flags.or_operation
(id, feature_availability_rule_id)
VALUES('ba9e28d5-fd1d-4375-a6ec-fede7b813d82', '85d8028f-d0aa-4422-8420-0dd4afb23a95');
INSERT INTO FeatureFlagsDB.feature_flags.or_operation
(id, feature_availability_rule_id)
VALUES('e2b6939f-4de8-4cef-a3c9-3cb4e02167e6', '8901771a-659f-4287-a4cd-69c32303ebe5');

INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('512b908e-58c5-4363-b76b-fb2de521d936', 'IS_NOT', 'mmacan', 'userName', '9a597dcb-e0f3-4114-a603-54605dd910ab');
INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('79176f71-b3dc-428c-90b2-e42c5da95fc2', 'IS', 'Marko', 'firstName', 'ba9e28d5-fd1d-4375-a6ec-fede7b813d82');
INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('84b07ff9-5424-4d35-bf51-991b3d54d076', 'IS', 'Peric', 'lastName', 'a450d3cf-e7b7-4e53-bb57-0425460e2207');
INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('998ff9c1-9947-46f4-8d54-5abd747dfbbe', 'IS_NOT', 'BlaBla', 'lastName', 'e2b6939f-4de8-4cef-a3c9-3cb4e02167e6');
INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('9ff3d642-5b8e-46cf-b4ec-c86a09063386', 'IS', 'Split', 'city', '9a597dcb-e0f3-4114-a603-54605dd910ab');
INSERT INTO FeatureFlagsDB.feature_flags.and_operation
(id, [condition], criteria, group_strategy, or_operation_id)
VALUES('e1a2789e-9829-4da0-a625-a3f2ab5daa4b', 'IS', 'Sinj', 'city', 'ba9e28d5-fd1d-4375-a6ec-fede7b813d82');

INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('85d8028f-d0aa-4422-8420-0dd4afb23a95', '9b1e8d46-9111-4795-a2e9-34bc6af4fe85');
INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('85d8028f-d0aa-4422-8420-0dd4afb23a95', 'a20c1980-dbd4-4c90-b587-f89493d87440');
INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('8901771a-659f-4287-a4cd-69c32303ebe5', '9b1e8d46-9111-4795-a2e9-34bc6af4fe85');
INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('8901771a-659f-4287-a4cd-69c32303ebe5', 'a20c1980-dbd4-4c90-b587-f89493d87440');
INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('8901771a-659f-4287-a4cd-69c32303ebe5', 'a95013c6-c24d-44e6-b767-cd697a56046b');
INSERT INTO FeatureFlagsDB.feature_flags.participant
(feature_availability_rule_id, user_id)
VALUES('8901771a-659f-4287-a4cd-69c32303ebe5', 'fc6127a4-0507-4569-aa2c-df8aac5e41b2');
