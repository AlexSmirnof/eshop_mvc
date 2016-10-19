SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

START TRANSACTION
INSERT INTO Phones (model,color,displaySize,ph_length,width,camera,price) VALUES ('Samsung Galaxy S6 16GB','black','4"','14mm','56mm','12MP','250.00');
INSERT INTO Phones (model,color,displaySize,ph_length,width,camera,price) VALUES ('Samsung Galaxy S6 32GB','black','4"','14mm','56mm','12MP','300.00');
COMMIT;

-- INSERT INTO Phones (model,color,displaySize,price) VALUES ('Iphone 5','black','8"','500.00');
-- INSERT INTO Phones (model,color,displaySize,price) VALUES ('IPhone 6','black','8"','650.00');
-- COMMIT;
-- START TRANSACTION
-- INSERT INTO Orders (firstName,lastName,deliveryAddress,contactPhoneNo,totalPrice) VALUES ('Alex','XXX','YYY','7654321','1000.00');
-- INSERT INTO Orders (firstName,lastName,deliveryAddress,contactPhoneNo,totalPrice) VALUES ('Ann','XXX','YYY','1234567','1650.00');
-- COMMIT;
-- START TRANSACTION
-- INSERT INTO OrderItems (phone_id, quantity, order_id) VALUES ('1','2','1');
-- INSERT INTO OrderItems (phone_id, quantity, order_id) VALUES ('3','1','1');
-- INSERT INTO OrderItems (phone_id, quantity, order_id) VALUES ('2','2','2');
-- INSERT INTO OrderItems (phone_id, quantity, order_id) VALUES ('4','1','2');
-- COMMIT;


