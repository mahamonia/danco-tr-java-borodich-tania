select p.model
from (
  select model, price from pc
  union
  select model, price from laptop
  union
  select model, price from printer
) p
where p.price = (
   select max(p1.price) price
   from (
     select price from pc
     union
     select price from laptop
     union
     select price from printer
   ) p1
)
