select distinct product.type, laptop.model, laptop.speed
from laptop join product
on laptop.model= product.model 
where laptop.speed < (select min(pc.speed) from pc)