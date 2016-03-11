select product.maker, max(pc.price) as max_price
from product join pc
on product.model = pc.model

group by product.maker