TRUNCATE TABLE authors CASCADE;
TRUNCATE TABLE books;

-- DECLARE
--     author_rec authors%rowtype;
-- BEGIN
--     Select * into author_rec from authors where id = 1234;
--     dbms_output.put_line('Author_id ' || author_rec.id);
-- END;
-- /