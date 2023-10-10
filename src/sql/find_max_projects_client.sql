select client.name as name, count(project.id) as project_count
from client inner join project
on client.id = project.client_id
group by client_id
having project_count = (select count(id) as count
from project
group by client_id
order by count desc
limit 1);