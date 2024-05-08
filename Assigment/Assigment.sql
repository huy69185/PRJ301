create table Registration(
username nvarchar(20) primary key not null,
password nvarchar(30) not null,
lastname nvarchar(100) not null,
isAdmin bit not null
);
INSERT INTO [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES
('huyhnn', '123456', 'Nhat Huy', 1),
('nguyenmk', '12345', 'Khoi Nguyen', 1),
('sonnx', '123456', 'Xuan Son', 0),
('thanhnm', '123456', 'Minh Thanh', 0),
('huannd', '123456', 'Duc Huan', 1),
('thanhph', '12345', 'Huu Thanh', 1),
('nguyen', '123456', 'Nguyen', 0),
('huy', '1234567', 'Huy', 0),


CREATE TABLE [dbo].[Product](
    [sku] [varchar](5) PRIMARY KEY,
    [name] [nvarchar](250) NOT NULL,
    [description] [nvarchar](250) NOT NULL,
    [unitPrice] [float] NOT NULL,
    [quantity] [int] NOT NULL,
    [status] [bit] NOT NULL
);

-- Table: tbl_Order
CREATE TABLE [dbo].[Order](
    [id] [varchar](5) PRIMARY KEY,
    [date] [datetime] NOT NULL,
    [total] [float] NOT NULL
);


CREATE TABLE [dbo].[OrderDetail](
    [id] [int] IDENTITY(1,1) PRIMARY KEY,
    [product_id] [varchar](5) NOT NULL,
    [order_id] [varchar](5) NOT NULL,
    [unitprice] [float] NOT NULL,
    [quantity] [int] NOT NULL,
    [total] [float] NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Product(sku),
    FOREIGN KEY (order_id) REFERENCES [Order](id)
);
UPDATE [dbo].[OrderDetail]
SET total = ROUND(total, 4)

-- Chèn dữ liệu vào bảng Product
INSERT INTO dbo.Product (sku, name, description, unitPrice, quantity, status) VALUES
('SKU01', 'Ao so mi nam', 'Ao so mi nam mau trang', 200.00, 50, 1),
('SKU02', 'Quan jean nu', 'Quan jean nu size 28', 235.50, 75, 1),
('SKU03', 'Giay the thao', 'Giay the thao nam size 42', 1199.99, 30, 1),
('SKU04', 'Tui xach nu', 'Tui xach nu mau den', 513.65, 20, 1),
('SKU05', 'Dong ho nam', 'Dong ho nam chong nuoc', 300.35, 15, 1),
('SKU06', 'Kinh mat', 'Kinh mat chong tia UV', 275.15, 100, 1),
('SKU07', 'Nuoc hoa nu', 'Nuoc hoa nu huong hoa', 550.73, 25, 1),
('SKU08', 'Mu bao hiem', 'Mu bao hiem fullface', 315.52, 60, 1),
('SKU09', 'Day nit nam', 'Day nit nam da bo', 125.10, 80, 1),
('SKU10', 'Vi nu', 'Vi nu da bo', 95.68, 40, 1);
