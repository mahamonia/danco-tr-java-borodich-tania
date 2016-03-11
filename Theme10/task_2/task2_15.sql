select pc.hd
from pc
group by pc.hd
having count(model)>1