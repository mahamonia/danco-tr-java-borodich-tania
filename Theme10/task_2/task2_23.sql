select product.maker
from product join pc
on product.model = pc.model
where pc.speed>=750

union
select product.maker
from product join laptop
on product.model = laptop.model
where laptop.speed >=750