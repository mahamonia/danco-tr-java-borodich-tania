select max(pc.model) as model_max, min(pc.model) as model_min, pc.speed, pc.ram
from pc
group by pc.speed, pc.ram

