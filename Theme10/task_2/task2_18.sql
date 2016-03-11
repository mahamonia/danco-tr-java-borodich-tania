select product.maker, min(printer.price) as price
from product join printer
on product.model = printer.model
where printer.color = 'y'