CREATE OR REPLACE TRIGGER DELETE_EMPLOYEE
AFTER DELETE ON EMPLOYEES
FOR EACH ROW
BEGIN
    DELETE FROM LEAVE
    WHERE EMPLOYEE_ID=:OLD.EMPLOYEE_ID;
    
    DELETE FROM PAYMENTS
    WHERE EMPLOYEE_ID=:OLD.EMPLOYEE_ID;
END;