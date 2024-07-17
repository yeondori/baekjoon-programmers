-- 코드를 작성해주세요
# with codes as ( 
#     select code from skillcodes where category like 'Python' py_code,
#     select code from skillcodes where category like 'C#' c_code,
#     select sum(code) from skillcodes where category like 'Front End' fe_code
# )
with codes as (
    select 
        (select code from skillcodes where name = 'Python') py_code,
        (select code from skillcodes where name = 'C#') c_code,
        (select sum(code) from skillcodes where category = 'Front End') fe_code
    from skillcodes
)
select 
(case 
when (skill_code & py_code and skill_code & fe_code) then 'A'
when skill_code & c_code then 'B'
when skill_code & fe_code then 'C'
end) as grade, id, email
from developers, codes
group by grade, id, email
having grade is not null
order by grade, id;