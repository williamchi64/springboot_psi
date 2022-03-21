SELECT p.id, p.name, p.cost, p.price, 
(SELECT sum(amount) FROM purchase_items WHERE product_id = p.id limit 1) as amount1, 
(SELECT sum(amount) FROM order_items WHERE product_id = p.id limit 1) as amount2 
FROM products p
