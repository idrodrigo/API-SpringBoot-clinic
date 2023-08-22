alter table doctor
    add isActive tinyint;
update doctor set isActive = 1;

--   delete from flyway_schema_history where success = 0;