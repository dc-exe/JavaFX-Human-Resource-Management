create or replace PROCEDURE employee_project1 (
  emp_id   in  EMPLOYEES.EMPLOYEE_ID%TYPE,
  cur      out SYS_REFCURSOR
)
IS
BEGIN
  OPEN cur FOR
    SELECT p.project_id,
           p.project_name
    FROM   employees e
           INNER JOIN departments d
           ON ( e.department_id = d.department_id )
           INNER JOIN projects p
           ON ( p.department_id = e.department_id)
    WHERE  e.employee_id = emp_id;
END;