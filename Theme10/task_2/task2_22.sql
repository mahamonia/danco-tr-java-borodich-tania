select pc.speed, avg(pc.price) as avg_price
from pc
group by pc.speed
having pc.speed >600