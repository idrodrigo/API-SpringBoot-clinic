alter table patient
    add isActive tinyint;
update patient set isActive = 1;

--   delete from flyway_schema_history where success = 0;