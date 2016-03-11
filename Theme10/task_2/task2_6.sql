select distinct  product.maker, laptop.speed
from product join laptop
where laptop.hd > 10