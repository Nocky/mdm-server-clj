COMMIT;
--;;
Do
$do$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'mdm') THEN
      CREATE database mdm;
   END IF;
END
$do$;
--;;
begin;
