CREATE OR REPLACE TRIGGER emp_count_department
AFTER INSERT OR DELETE ON EMPLOYEES
for each row
DECLARE
    counter NUMBER;
BEGIN
    if inserting then
        select total_employees into counter
        from departments
        where department_id = :new.department_id;
    
        update departments
        set total_employees = counter+1
        where department_id = :new.department_id;
        
    elsif deleting then
        select total_employees into counter
        from departments
        where department_id = :old.department_id;
        
        update departments
        set total_employees = counter-1
        where department_id = :old.department_id;
    end if;
END;
