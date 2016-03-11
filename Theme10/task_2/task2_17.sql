select distinct laptop.model, laptop.speed
from laptop join pc
where laptop.speed < (select min(pc.speed) from pc)