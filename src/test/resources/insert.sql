-- Books
INSERT INTO products (productId, category, brand) VALUES
('B001', 'Books', 'Penguin'),
('B002', 'Books', 'HarperCollins'),
('B003', 'Books', 'Random House'),
('B004', 'Books', 'Scholastic'),
('B005', 'Books', 'Simon & Schuster');

-- Electronics
INSERT INTO products (productId, category, brand) VALUES
('E001', 'Electronics', 'Sony'),
('E002', 'Electronics', 'LG'),
('E003', 'Electronics', 'Panasonic'),
('E004', 'Electronics', 'Bose'),
('E005', 'Electronics', 'JBL');

-- Babies
INSERT INTO products (productId, category, brand) VALUES
('BY001', 'Babies', 'Fisher-Price'),
('BY002', 'Babies', 'Graco'),
('BY003', 'Babies', 'Gerber'),
('BY004', 'Babies', 'Huggies'),
('BY005', 'Babies', 'Pampers');

-- Groceries
INSERT INTO products (productId, category, brand) VALUES
('G001', 'Groceries', 'Kraft'),
('G002', 'Groceries', 'Nestle'),
('G003', 'Groceries', 'Kelloggs'),
('G004', 'Groceries', 'Heinz'),
('G005', 'Groceries', 'Campbells');

---Shoppers

INSERT INTO shoppers (shopperId, name) VALUES
('S001', 'Priya Patel'),
('S002', 'Rahul Sharma'),
('S003', 'Nisha Gupta'),
('S004', 'Rajesh Singh'),
('S005', 'Ananya Das'),
('S006', 'Vivek Mehta'),
('S007', 'Aarti Verma'),
('S008', 'Rohan Choudhury'),
('S009', 'Kavita Reddy'),
('S010', 'Arjun Desai');


-- Random combinations with shoppers and products
INSERT INTO shopper_product_relevance (shopperId, productId, relevancyScore) VALUES
('S001', 'B001', 50.123456789012345),
('S001', 'E001', 60.234567890123456),
('S001', 'BY001', 70.345678901234567),
('S001', 'G001', 80.456789012345678),
('S002', 'B002', 90.567890123456789),
('S002', 'E002', 45.678901234567890),
('S002', 'BY002', 55.789012345678901),
('S002', 'G002', 65.890123456789012),
('S003', 'B003', 75.901234567890123),
('S003', 'E003', 85.012345678901234),
('S003', 'BY003', 95.123456789012345),
('S003', 'G003', 37.234567890123456),
('S004', 'B004', 47.345678901234567),
('S004', 'E004', 57.456789012345678),
('S004', 'BY004', 67.567890123456789),
('S004', 'G004', 77.678901234567890),
('S005', 'B005', 87.789012345678901),
('S005', 'E005', 97.890123456789012),
('S005', 'BY005', 36.901234567890123),
('S005', 'G005', 46.012345678901234),
('S006', 'B001', 56.123456789012345),
('S006', 'E002', 66.234567890123456),
('S006', 'BY003', 76.345678901234567),
('S006', 'G004', 86.456789012345678),
('S007', 'B005', 96.567890123456789),
('S007', 'E001', 45.678901234567890),
('S007', 'BY002', 55.789012345678901),
('S007', 'G003', 65.890123456789012),
('S008', 'B004', 75.901234567890123),
('S008', 'E005', 85.012345678901234),
('S008', 'BY001', 95.123456789012345),
('S008', 'G002', 37.234567890123456),
('S009', 'B003', 47.345678901234567),
('S009', 'E004', 57.456789012345678),
('S009', 'BY005', 67.567890123456789),
('S009', 'G001', 77.678901234567890),
('S010', 'B002', 87.789012345678901),
('S010', 'E003', 97.890123456789012),
('S010', 'BY004', 36.901234567890123),
('S010', 'G005', 46.012345678901234);