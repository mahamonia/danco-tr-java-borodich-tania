select pc.model as model, price
from pc
where pc.price = (select max(price) from pc)

union
select printer.model as model, price
from printer
where printer.price = (select max(price) from printer)

union
select laptop.model as model, price
from laptop
where laptop.price = (select max(price) from laptop)




