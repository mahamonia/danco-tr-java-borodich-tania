select product.maker, avg(laptop.screen) as avg_screen
from product join laptop
on product.model = laptop.model
group by product.maker