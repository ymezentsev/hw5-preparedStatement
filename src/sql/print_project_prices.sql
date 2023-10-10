select project.id as name,
((year(finish_date)-year(start_date))*12 + month(finish_date)-month(start_date)) * sum(salary) as price
from project inner join project_worker
on project.id = project_id
inner join worker on worker.id = worker_id
group by project.id
order by price desc