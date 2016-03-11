select product.maker, count(product.model) as count_model
from product
where product.type = 'pc' 
group by product.maker
having count(product.model)>=3

