select distinct maker
from product
where type = 'printer' and maker in 
	(select maker 
    from product inner join pc 
    on product.model = pc.model 
    where ram = (
		select min(ram) from pc) and speed = 
          (select max(speed) from pc 
          where ram = (select min(ram) from pc)))