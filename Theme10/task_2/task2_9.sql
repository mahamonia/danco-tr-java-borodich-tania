select distinct product.maker
from product join pc
on product.model = pc.model
where pc.speed >= 450