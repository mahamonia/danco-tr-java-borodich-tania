select model, speed, hd
from pc
where (cd = '12x' or cd = '24x') 
and price < 600