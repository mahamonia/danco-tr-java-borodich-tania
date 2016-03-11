select distinct maker
from product
where type = 'pc' and 
maker not in 
(select distinct maker
from product
where type = 'laptop') 