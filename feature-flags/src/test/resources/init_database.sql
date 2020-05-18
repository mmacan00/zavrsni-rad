IF NOT EXISTS(SELECT 1
              FROM sys.schemas
              WHERE name = 'feature_flags')
    EXEC ('CREATE SCHEMA [feature_flags]');
