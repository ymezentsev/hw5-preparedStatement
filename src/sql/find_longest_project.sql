select id as name,
((year(finish_date)-year(start_date))*12 + month(finish_date)-month(start_date)) as month_count
from project
where ((year(finish_date)-year(start_date))*12 + month(finish_date)-month(start_date)) = (select
((year(finish_date)-year(start_date))*12 + month(finish_date)-month(start_date)) as max
from project
order by max desc
limit 1)