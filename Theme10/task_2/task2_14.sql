select avg(pc.price) as avg_price, speed
from pc
group by pc.speed