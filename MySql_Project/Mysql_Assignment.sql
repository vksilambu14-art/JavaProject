USE banking;

SELECT * FROM accounts WHERE balance<20000;

SELECT * From customers WHERE city='Chennai';

SELECT * FROM accounts WHERE balance BETWEEN 20000 AND 50000;

SELECT * FROM customers WHERE name LIKE 'J%';

SELECT * FROM accounts WHERE account_type ='Savings' OR account_type='Current';

SELECT * FROM accounts where account_type !='Savings';

SELECT * FROM customers WHERE name LIKE '%a%';

SELECT * FROM accounts WHERE balance<=30000;

SELECT * FROM customers where city!='Madurai';

SELECT * FROM accounts WHERE balance NOT BETWEEN 10000 AND 50000;

SELECT * FROM customers WHERE name LIKE '%i';

SELECT * FROM accounts WHERE balance= 50000;

SELECT * FROM customers WHERE city='Chennai' OR city='Salem';

SELECT * FROM accounts WHERE balance > 10000 AND balance < 40000;

SELECT * FROM accounts WHERE account_type !='Current';

SELECT * FROM accounts ORDER BY balance DESC;

SELECT * FROM customers ORDER BY name ASC;

SELECT * FROM accounts ORDER BY account_type ASC, balance DESC;

SELECT SUM(balance) AS Total_Amount FROM accounts;

SELECT AVG(balance) AS Average_Amount FROM accounts; 

SELECT MAX(balance) AS Maximum_Amount FROM accounts;

SELECT MIN(balance) AS Minimum_Amount FROM accounts;

SELECT COUNT(*) AS Total_Customer FROM customers;

SELECT account_type, SUM(balance) AS Total_Amount FROM accounts GROUP BY account_type;

SELECT account_type,AVG(balance) AS Average_Amont FROM accounts GROUP By account_type;

SELECT account_type,AVG(balance) AS Average_balance FROM accounts GROUP By account_type HAVING Average_balance>20000;

SELECT customer_id,COUNT(*)  AS Total_Accounts FROM accounts GROUP BY customer_id;

SELECT customer_id,count(*) FROM accounts GROUP By  customer_id HAVING COUNT(*)>1;

 SELECT c.name, a.balance FROM customers c LEFT JOIN accounts a ON c.customer_id = a.customer_id;

SELECT c.*,a.* FROM customers c LEFT JOIN accounts a on c.customer_id=a.customer_id;

SELECT * FROM accounts a RIGHT JOIN  customers c on c.customer_id=a.customer_id;

SELECT c.name,a.account_type FROM customers c LEFT JOIN accounts a on c.customer_id=a.customer_id WHERE balance>20000;

SELECT c.customer_id,c.name,sum(balance) as Total_Balance from customers c  JOIN accounts a on c.customer_id=a.customer_id GROUP BY c.customer_id,c.name;

SELECT c.name,a.balance   FROM customers c JOIN accounts a ON c.customer_id=a.customer_id ORDER BY balance ASC ;

SELECT COUNT(account_id) AS Account_counts,c.city FROM accounts a JOIN customers c ON c.customer_id=a.customer_id GROUP By c.city;

SELECT * FROM accounts WHERE balance>(Select AVG(balance) from accounts);

SELECT * FROM customers WHERE customer_id IN(select customer_id FROM accounts);

SELECT * FROM customers WHERE customer_id NOT IN(select customer_id FROM accounts WHERE customer_id is  NOT null);

SELECT * FROM  accounts WHERE balance= (SELECT max(balance) from accounts);

SELECT c.customer_id,c.name,SUM(a.balance)AS Total_balance FROM customers c JOIN accounts a ON c.customer_id = a.customer_id GROUP by c.customer_id,c.name HAVING Sum(a.balance)>40000;


-- Railway
CREATE DATABASE RAILWAY;
USE railway;

CREATE TABLE trains(train_id int AUTO_INCREMENT PRIMARY KEY,train_name VARCHAR(50),source VARCHAR(50),destination VARCHAR(50));

CREATE TABLE booking(booking_id INT PRIMARY KEY AUTO_INCREMENT,train_id int,passenger_name VARCHAR(50),fare DECIMAL(10,2),status varchar(20),foreign KEY(train_id) REFERENCES trains(train_id));

INSERT INTO trains (train_name, source, destination) VALUES('Express1','Chennai','Madurai'),('Express2','Coimbatore','Salem'),('Express3','Madurai','Chennai'),('Express4','kaniyakumari','Chennai'),('Express5','coimbatore','Chennai');

INSERT INTO booking(train_id, passenger_name, fare, status) VALUES(1,'Silambu',500,'Confirmed'),(1,'Siva',500,'Waiting'),(2,'Priya',300,'Confirmed'),(3,'Kumar',450,'Cancelled'),(4,'Sakthi',357,'Waiting'),(5,'Sridhar',789,'Confirmed'),(2,'Meena',300,'Confirmed');

SELECT * FROM booking WHERE fare>400;

SELECT* FROM booking WHERE status!='Confirmed';

SELECT * FROM trains WHERE source='Chennai';

SELECT * FROM booking WHERE fare BETWEEN 300 AND 500;

SELECT * FROM booking WHERE passenger_name like 'S%';

SELECT t.train_name,b.passenger_name FROM trains t JOIN booking b on t.train_id=b.train_id;

SELECT t. train_id,t.train_name,COUNT(booking_id) as Total_booking FROM trains t LEFT JOIN  booking b on t.train_id=b.train_id GROUP by t.train_name,t.train_id;

SELECT t.train_name ,sum(fare) AS Total_fare from trains t join booking b on t.train_id=b.train_id  GROUP by t.train_id,t.train_name  ;

SELECT * FROM booking where fare=(select max(fare) from booking);

SELECT t.train_id,t.train_name,count(booking_id)  AS Total_bookings FROM trains t  join  booking b on t.train_id=b.train_id GROUP BY t.train_id,t.train_name HAVING count(booking_id)>1;

-- EMPLOYEE MANAGEMENT 
CREATE DATABASE EMPLOYEE;
USE employee;
CREATE TABLE Employee (emp_id INT PRIMARY KEY AUTO_INCREMENT,emp_name VARCHAR(50),department VARCHAR(30),salary DECIMAL(10,2),city VARCHAR(30),joining_date DATE)auto_increment=101;

INSERT INTO Employee (emp_name,department,salary,city,joining_date)VALUES
('Silambu','IT',60000,'Chennai','2022-01-15'),
('David','HR',45000,'Bangalore','2021-03-10'),
('Siva','IT',70000,'Chennai','2020-07-12'),
('Mary','Finance',55000,'Mumbai','2023-01-20'),
('Kumar','HR',48000,'Delhi','2022-05-05'),
('Gokul','Finance',65000,'Mumbai','2021-08-18');

SELECT department, count(emp_id) AS Total_Employees from employee GROUP by department;

SELECT department,avg(salary) AS Averagr_salary from employee GROUP by department;

SELECT department,count(*) AS Total_Employee FROM employee GROUP by department HAVING count(*)>1;

SELECT department,max(salary) as Maximum_salary FROM employee GROUP by department;

SELECT department,Min(salary)as Minimum_Salary FROM employee GROUP by department;

SELECT department, AVG(salary) AS Average_Salary FROM employee GROUP by department HAVING avg(salary)>50000;

SELECT  department ,sum(salary) AS Total_Salary FROM employee GROUP by  department;

SELECT * from employee ORDER BY salary DESC;

SELECT *FROM employee ORDER BY department ASC, salary DESC;

SELECT city, count(*) as Total_employee FROM employee GROUP by city HAVING count(*)>1;

SELECT city,sum(salary) AS Total_salary from employee GROUP by city;

SELECT department ,sum(salary) As Total_Salary from employee GROUP By department ORDER BY sum(salary) DESC;

SELECT department,COUNT(*) AS Total_Employee from employee where salary>50000 GROUP by department ;

SELECT  department,MAX(salary) as Highest_salary,min(salary) as Lowest_Salary, MAX(salary) - MIN(salary) AS salary_difference from employee GROUP by department;

SELECT * from employee ORDER BY salary DESC LIMIT 3;

-- Customers & Orders Tables

CREATE DATABASE CUSTOMER_ORDER;
USE customer_order;
CREATE TABLE Customers (customer_id INT PRIMARY KEY AUTO_INCREMENT,customer_name VARCHAR(50),city VARCHAR(30))auto_increment=101;

CREATE TABLE Orders (order_id INT PRIMARY KEY AUTO_INCREMENT,customer_id INT,amount DECIMAL(10,2),order_date DATE,FOREIGN KEY(customer_id) REFERENCES Customers(customer_id));

INSERT INTO customers ( customer_name, city)VALUES( 'Silambu', 'Chennai'),( 'Priya', 'Salem'),( 'Kumar', 'Coimbatore'),( 'Divya', 'Madurai'),( 'Siva', 'Chennai'),( 'Gokul', 'Trichy');

INSERT INTO Orders (customer_id, amount, order_date)VALUES(101, 2500.00, '2026-10-01'),(102, 1500.00, '2026-10-02'),(101, 3000.00, '2026-10-03'),(103, 4500.00, '2026-10-04'),(104, 2000.00, '2026-10-05'),(102, 3500.00, '2026-10-06'),(105, 1000.00, '2026-10-07');

SELECT  c.customer_id,c.customer_name,sum(amount) as Total_orderAmount from customers c  JOIN  orders o on o.customer_id=c.customer_id GROUP by c.customer_id,c.customer_name;

SELECT c.customer_id, c.customer_name, COUNT(o.order_id) AS total_orders FROM Customers c JOIN Orders o ON c.customer_id = o.customer_id GROUP BY c.customer_id, c.customer_name HAVING COUNT(o.order_id) > 3;

SELECT c.customer_id,c.customer_name, AVG(o.amount) as Average_Amount from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name;

SELECT c.customer_id,c.customer_name, MAX(o.amount) as Highest_orderAmount from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name;

SELECT c.customer_id,c.customer_name, SUM(o.amount) AS Total_purchaseAmount  from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name ORDER BY SUM(o.amount) DESC;

SELECT c.customer_id,c.customer_name, SUM(o.amount) AS Total_purchaseAmount  from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name HAVING SUM(o.amount) >10000;

SELECT c.customer_id,c.customer_name, COUNT(o.order_id) AS Total_Order  from customers c LEFT JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name;

SELECT c.customer_id,c.customer_name, SUM(o.amount) as Heighest_orderAmount from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name ORDER BY sum(o.amount) DESC LIMIT 1;

SELECT c.customer_id,c.customer_name, count(o.order_id) as total_orders from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name ORDER BY count(o.order_id) DESC  LIMIT 1;

SELECT c.customer_id,c.customer_name, AVG(o.amount) as Average_Amount from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name HAVING AVG(o.amount)>2000;

SELECT c.customer_id,c.customer_name, SUM(o.amount) as Total_PurchaseAmount from customers c JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name ORDER BY sum(o.amount) DESC LIMIT 5 ;

SELECT c.customer_id,c.customer_name,Min(o.amount) as Minimum_orderAmount from customers c LEFT JOIN orders o on c.customer_id=o.customer_id GROUP by c.customer_id,c.customer_name ;

SELECT c.customer_id, c.customer_name,SUM(o.amount) AS Total_Spent FROM Customers c JOIN Orders o ON c.customer_id = o.customer_id GROUP BY c.customer_id, c.customer_name HAVING SUM(o.amount)>5000;

SELECT c.customer_id, c.customer_name,count(o.order_id) AS Total_order,sum(o.amount) AS Total_Amount FROM Customers c LEFT JOIN Orders o ON c.customer_id = o.customer_id GROUP BY c.customer_id, c.customer_name;

SELECT c.customer_id, c.customer_name,count(o.order_id) AS Total_order,sum(o.amount) AS Total_Amount FROM Customers c LEFT JOIN Orders o ON c.customer_id = o.customer_id GROUP BY c.customer_id, c.customer_name HAVING COUNT(o.order_id)>2 and sum(o.amount)>8000;

-- Student Management

CREATE DATABASE StudentData;
USE studentdata;

CREATE TABLE Students (student_id INT PRIMARY KEY AUTO_INCREMENT,student_name VARCHAR(50),department VARCHAR(30),marks INT) AUTO_INCREMENT=101;

INSERT INTO Students (student_name, department, marks) VALUES 
('Silambu', 'Computer Science', 85),
('Priya Sharma', 'Information Technology', 92),
('Sridhar', 'Electronics', 78),
('Divya Ramesh', 'Computer Science', 88),
('Kumar Vel', 'Mechanical', 65),
('Anitha Raj', 'Civil', 90),
('Sanjay Karthik', 'Information Technology', 74),
('Meena Viji', 'Electronics', 82),
('Gokul', 'Computer Science', 95),
('Baalamurugan', 'Mechanical', 70);

SELECT department,AVG(marks)  as Average_mark FROM students GROUP by department ;

SELECT department,AVG(marks)  as Average_mark FROM students GROUP by department HAVING AVG(marks)>75;

SELECT department,max(marks)  as Heighest_mark FROM students GROUP by department ;

SELECT department, COUNT(student_id) as Total_student FROM students GROUP BY department;

SELECT department, COUNT(student_id) as Total_student FROM students GROUP BY department HAVING count(student_id)>5;

SELECT department,AVG(marks)  as Average_mark FROM students GROUP by department ORDER BY avg(marks)desc ;

SELECT department,AVG(marks)  as Average_mark FROM students GROUP by department ORDER BY avg(marks)desc LIMIT 3 ;

SELECT department,AVG(marks)  as Average_mark FROM students GROUP by department HAVING AVG(marks) BETWEEN 70 and 90 ;

SELECT department, SUM(marks) as Total_marks FROM students GROUP BY department;

SELECT department, COUNT(student_id) as Total_student FROM students GROUP BY department ORDER BY total_student DESC;

SELECT department, MIN(marks) as Lowest_marks FROM students GROUP BY department;

SELECT department, Max(marks) as heighest_marks FROM students GROUP BY department HAVING MAX(marks)>90;

SELECT department,COUNT(student_id) AS score_above80 FROM students where marks>80 GROUP BY department ;

SELECT department,COUNT(student_id) AS score_above70 FROM students where marks>75 GROUP BY department HAVING COUNT(student_id)>3 ;
 
SELECT department, Max(marks) as heighest_marks FROM students GROUP BY department ORDER BY MAX(marks) DESC;

