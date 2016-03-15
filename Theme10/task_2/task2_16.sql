select distinct B.model as model, A.model as model, A.speed, A.ram 
from PC as A, PC B 
where A.speed = B.speed and A.ram = B.ram and A.model < B.model

