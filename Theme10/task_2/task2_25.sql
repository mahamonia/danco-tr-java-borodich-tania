select distinct product.maker, min(pc.ram), max(pc.speed), product.type
from product join pc
where product.type = 'printer' and 
maker in 
(select distinct maker
from product
where type = 'pc') 
