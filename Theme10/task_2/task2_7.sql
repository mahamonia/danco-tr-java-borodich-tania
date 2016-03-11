select product.model, laptop.price
from product join laptop 
where product.model = laptop.model
and product.maker = 'B'

union
select product.model, pc.price
from product join pc
where product.model = pc.model
and product.maker = 'B'

union
select product.model, printer.price
from product join printer
where product.model = printer.model
and product.maker = 'B'
