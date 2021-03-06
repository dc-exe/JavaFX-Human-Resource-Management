CREATE OR REPLACE TRIGGER T_LEAVE 
AFTER INSERT OR UPDATE ON LEAVE
FOR EACH ROW
BEGIN 
IF INSERTING THEN
    IF :NEW.ATTENDANCE<22 OR :NEW.ATTENDANCE>26 THEN
        RAISE_APPLICATION_ERROR(-20004,'ATTENDANCE SHOULD BE IN BETWEEN 22 AND 26...');
    END IF;
    IF :NEW.LEAVE<0 OR :NEW.LEAVE>5 THEN
        RAISE_APPLICATION_ERROR(-20004,'LEAVE SHOULD BE IN BETWEEN 0 AND 5...');
    END IF;
ELSIF UPDATING THEN
    IF :NEW.ATTENDANCE<22 OR :NEW.ATTENDANCE>26 THEN
        RAISE_APPLICATION_ERROR(-20004,'ATTENDANCE SHOULD BE IN BETWEEN 22 AND 26...');
    END IF;
    IF :NEW.LEAVE<0 OR :NEW.LEAVE>5 THEN
        RAISE_APPLICATION_ERROR(-20004,'LEAVE SHOULD BE IN BETWEEN 0 AND 4...');
    END IF;
 END IF;
 END;