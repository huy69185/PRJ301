create table Product (
	sku varchar(5) primary key,
	name nvarchar(250),
	description nvarchar(250),
	unitprice float,
	quantity int,
	status bit
);

CREATE TABLE tbl_Order (
    id VARCHAR(5) PRIMARY KEY,
    date DATETIME,
    total FLOAT
);

CREATE TABLE Order_Detail (
    id INT IDENTITY(1,1) PRIMARY KEY,
	product_id varchar(5),
	order_id varchar(5),
	unitprice float,
	quantity int,
	total float,
	FOREIGN KEY (product_id) REFERENCES Product(sku),
    FOREIGN KEY (order_id) REFERENCES tbl_Order(id)

);

INSERT INTO Product (sku, name, description, unitprice, quantity, status)
VALUES
    ('P001', 'White Tee', 'Description for a white tee', 19.99, 50, 1),
    ('P002', 'Blue Jeans', 'Description for blue jeans', 29.99, 40, 1),
    ('P003', 'Black Jacket', 'Description for a black jacket', 69.99, 20, 1),
    ('P004', 'Womens Bag', 'Description for a womens bag', 49.99, 30, 1),
    ('P005', 'White Shoes', 'Description for white shoes', 39.99, 60, 1),
    ('P006', 'Sports Watch', 'Description for a sports watch', 79.99, 15, 1),
    ('P007', 'White Shirt', 'Description for a white shirt', 24.99, 55, 1),
    ('P008', 'Black Pants', 'Description for black pants', 34.99, 25, 1),
    ('P009', 'Womens Sweater', 'Description for a women sweater', 44.99, 35, 1),
    ('P010', 'School Bag', 'Description for a school bag', 29.99, 40, 1);

INSERT INTO tbl_Order (id, date, total)
VALUES
    ('HD001', '2024-03-01 10:30:00', 150.25),
    ('HD002', '2024-03-01 11:45:00', 300.50),
    ('HD003', '2024-03-02 09:15:00', 200.75),
    ('HD004', '2024-03-02 14:20:00', 175.00),
    ('HD005', '2024-03-03 12:00:00', 400.00),
    ('HD006', '2024-03-03 15:30:00', 250.80),
    ('HD007', '2024-03-04 08:45:00', 180.60),
    ('HD008', '2024-03-04 13:10:00', 220.25),
    ('HD009', '2024-03-05 11:20:00', 350.90),
    ('HD010', '2024-03-05 14:45:00', 275.00);

INSERT INTO Order_Detail (product_id, order_id, unitprice, quantity, total)
VALUES
    ('P001', 'HD001', 10.99, 5, 54.95),
    ('P002', 'HD001', 20.99, 3, 62.97),
    ('P003', 'HD002', 15.50, 8, 124.00),
    ('P004', 'HD002', 30.75, 2, 61.50),
    ('P005', 'HD003', 25.00, 4, 100.00),
    ('P006', 'HD003', 12.25, 6, 73.50),
    ('P007', 'HD004', 18.99, 7, 132.93),
    ('P008', 'HD004', 22.50, 3, 67.50),
    ('P009', 'HD005', 35.49, 10, 354.90),
    ('P010', 'HD005', 40.00, 5, 200.00);

