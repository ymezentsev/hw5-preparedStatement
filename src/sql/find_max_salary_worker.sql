select name, salary
from worker
where salary = (select salary
from worker
order by salary desc
limit 1)