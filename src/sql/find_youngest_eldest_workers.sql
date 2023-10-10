select 'YOUNGEST' as type, name, birthday
from worker
where birthday = (select birthday
from worker
order by birthday desc
limit 1)
union
select 'ELDEST' as type, name, birthday
from worker
where birthday = (select birthday
from worker
order by birthday asc
limit 1)