ALTER TABLE doctor
ALTER COLUMN is_active SET DATA TYPE boolean
USING CASE WHEN is_active = 1 THEN true ELSE false END;

ALTER TABLE patient
ALTER COLUMN is_active SET DATA TYPE boolean
USING CASE WHEN is_active = 1 THEN true ELSE false END;