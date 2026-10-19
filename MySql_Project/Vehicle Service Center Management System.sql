
CREATE DATABASE Vehicle_Service; 
USE Vehicle_Service;

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(50),
    phone VARCHAR(15),
    city VARCHAR(30)
);

INSERT INTO Customers(customer_name, phone, city) VALUES
('Silambu', '9876543210', 'Chennai'),
('Priya', '9876543211', 'Bangalore'),
('Siva', '9876543212', 'Hyderabad'),
('Sneha', '9876543213', 'Coimbatore'),
('Karthik', '9876543214', 'Mumbai');


CREATE TABLE Vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    vehicle_number VARCHAR(15),
    vehicle_model VARCHAR(50),
    vehicle_type VARCHAR(30),
    FOREIGN KEY(customer_id) REFERENCES Customers(customer_id)
) AUTO_INCREMENT = 101;

INSERT INTO Vehicles(customer_id, vehicle_number, vehicle_model, vehicle_type) VALUES
(1, 'TN10AB1234', 'Hyundai i20', 'Car'),
(2, 'KA05XY5678', 'Honda City', 'Car'),
(3, 'TS08PQ4321', 'Royal Enfield', 'Bike'),
(4, 'TN22KL9090', 'Maruti Swift', 'Car'),
(5, 'MH12AA1111', 'TVS Apache', 'Bike');


CREATE TABLE Mechanics (
    mechanic_id INT PRIMARY KEY AUTO_INCREMENT,
    mechanic_name VARCHAR(50),
    specialization VARCHAR(50),
    experience INT
) AUTO_INCREMENT = 1001;

INSERT INTO Mechanics(mechanic_name, specialization, experience) VALUES
('Ramesh', 'Engine', 10),
('Suresh', 'Electrical', 8),
('Mahesh', 'General Service', 6),
('Ganesh', 'Painting', 12);


CREATE TABLE Service_Records (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    mechanic_id INT,
    service_type VARCHAR(50),
    service_date DATE,
    cost DECIMAL(10,2),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(vehicle_id),
    FOREIGN KEY (mechanic_id) REFERENCES Mechanics(mechanic_id)
);


INSERT INTO Service_Records(vehicle_id, mechanic_id, service_type, service_date, cost) VALUES
(101, 1001, 'Engine Repair', '2026-07-10', 8000),
(101, 1002, 'Electrical Repair', '2026-07-20', 3000),
(102, 1003, 'General Service', '2026-07-21', 2500),
(103, 1001, 'Engine Repair', '2026-07-15', 5000),
(104, 1003, 'General Service', '2026-07-18', 2200),
(105, 1002, 'Electrical Repair', '2026-06-30', 1800),
(102, 1004, 'Painting', '2026-07-25', 7000),
(103, 1003, 'General Service', '2026-07-27', 2000);

CREATE TABLE Bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    service_id INT,
    total_amount DECIMAL(10,2),
    payment_status VARCHAR(20),
    FOREIGN KEY(service_id)
    REFERENCES Service_Records(service_id)
);

INSERT INTO Bills(service_id,total_amount,payment_status)VALUES
(1,8000,'Paid'),
(2,3000,'Paid'),
(3,2500,'Pending'),
(4,5000,'Paid'),
(5,2200,'Paid'),
(6,1800,'Pending'),
(7,7000,'Paid'),
(8,2000,'Pending');

SELECT vehicle_number,vehicle_model,service_type,service_date FROM Vehicles
JOIN Service_Records ON Vehicles.vehicle_id=Service_Records.vehicle_id WHERE service_date=CURDATE();

SELECT mechanic_name, COUNT(service_id) AS Total_Services
FROM Mechanics JOIN Service_Records ON Mechanics.mechanic_id=Service_Records.mechanic_id
GROUP BY mechanic_name ORDER BY Total_Services DESC LIMIT 1;

SELECT customer_name, SUM(total_amount) AS Total_Bill
FROM Customers JOIN Vehicles ON Customers.customer_id=Vehicles.customer_id
JOIN Service_Records ON Vehicles.vehicle_id=Service_Records.vehicle_id
JOIN Bills ON Service_Records.service_id=Bills.service_id GROUP BY customer_name;

SELECT vehicle_number,vehicle_model FROM Vehicles
WHERE vehicle_id NOT IN(SELECT vehicle_id FROM Service_Records
WHERE service_date>=DATE_SUB(CURDATE(),INTERVAL 1 YEAR));

SELECT vehicle_number, service_type, service_date, cost
FROM Vehicles
JOIN Service_Records ON Vehicles.vehicle_id=Service_Records.vehicle_id
WHERE vehicle_number='TN10AB1234' ORDER BY service_date;

SELECT service_type, COUNT(*) AS Total
FROM Service_Records GROUP BY service_type;

SELECT customer_name, COUNT(service_id) AS Visits
FROM Customers
JOIN Vehicles ON Customers.customer_id=Vehicles.customer_id
JOIN Service_Records ON Vehicles.vehicle_id=Service_Records.vehicle_id
GROUP BY customer_name HAVING COUNT(service_id)>3;

SELECT customer_name, vehicle_number,total_amount
FROM Customers JOIN Vehicles ON Customers.customer_id=Vehicles.customer_id 
JOIN Service_Records ON Vehicles.vehicle_id=Service_Records.vehicle_id 
JOIN Bills ON Service_Records.service_id=Bills.service_id
WHERE payment_status='Pending';

SELECT service_type, COUNT(*) AS Frequency
FROM Service_Records GROUP BY service_type
ORDER BY Frequency DESC LIMIT 1;

SELECT MONTH(service_date) AS Month, SUM(total_amount) AS Revenue
FROM Service_Records JOIN Bills
ON Service_Records.service_id=Bills.service_id GROUP BY MONTH(service_date);

SELECT * FROM Bills ORDER BY total_amount DESC LIMIT 1;

SELECT mechanic_name, SUM(total_amount) AS Revenue
FROM Mechanics JOIN Service_Records
ON Mechanics.mechanic_id=Service_Records.mechanic_id
JOIN Bills ON Service_Records.service_id=Bills.service_id
GROUP BY mechanic_name ORDER BY Revenue DESC;

SELECT customer_name, COUNT(vehicle_id) AS Vehicles
FROM Customers JOIN Vehicles ON Customers.customer_id=Vehicles.customer_id GROUP BY customer_name;


SELECT AVG(cost) AS Average_Cost FROM Service_Records;

SELECT * FROM Mechanics ORDER BY experience DESC LIMIT 1;


SELECT * FROM Service_Records WHERE service_type='Engine Repair';

SELECT *FROM Customers WHERE city='Chennai';

SELECT mechanic_name,service_type,service_date
FROM Mechanics JOIN Service_Records ON Mechanics.mechanic_id=Service_Records.mechanic_id
WHERE mechanic_name='Ramesh';

SELECT bill_id, customer_name,vehicle_number,service_type,service_date,total_amount
FROM Bills JOIN Service_Records ON Bills.service_id=Service_Records.service_id
JOIN Vehicles ON Service_Records.vehicle_id=Vehicles.vehicle_id
JOIN Customers ON Vehicles.customer_id=Customers.customer_id
WHERE payment_status='Pending';


SELECT mechanic_name,COUNT(service_id) AS Total_Services,RANK() OVER(ORDER BY COUNT(service_id) DESC) AS Service_Rank
FROM Mechanics LEFT JOIN Service_Records ON Mechanics.mechanic_id=Service_Records.mechanic_id
GROUP BY mechanic_name;



