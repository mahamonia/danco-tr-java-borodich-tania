select avg(pc.speed) as speed
from product join pc
on product.model = pc.model
where product.maker = 'A'