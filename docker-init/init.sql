CREATE TABLE IF NOT EXISTS departments (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS employees (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  phone_number VARCHAR(45),
  email VARCHAR(255) ,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  department_id INTEGER NOT NULL,
  CONSTRAINT fk_employee_department
    FOREIGN KEY (department_id)
      REFERENCES departments(id)
      ON DELETE CASCADE
);

INSERT INTO departments (name, creation_date) VALUES
    ('IT', '2024-10-27 10:00:00+02'),
	('Marketing', '2023-09-16 19:30:00+02'),
	('Management', '2020-01-27 15:46:21+02'),
	('HR', '2021-12-31 06:41:00+02'),
	('Sales', '1995-10-27 00:01:16+02');


INSERT INTO employees (full_name, address, phone_number, email, department_id) VALUES
     (
        'Alice Smith',
        '123 Main St, Vienna, Austria',
        '+43 1 2345678',
        'alice.smith@example.com',
        (SELECT id FROM departments WHERE name = 'IT')
      ),
      (
        'Bob Johnson',
        '456 Second Ave, Salzburg, Austria',
        '+43 662 987654',
        'bob.johnson@example.com',
        (SELECT id FROM departments WHERE name = 'Marketing')
      ),
      (
        'Catherine Lee',
        '789 Third Blvd, Graz, Austria',
        '+43 316 555444',
        'catherine.lee@example.com',
        (SELECT id FROM departments WHERE name = 'Sales')
      ),
      (
        'David Müller',
        '10 Fourth Rd, Innsbruck, Austria',
        '+43 512 123789',
        'david.mueller@example.com',
        (SELECT id FROM departments WHERE name = 'IT')
      ),
      (
        'Eva Novak',
        '22 Fifth St, Linz, Austria',
        '+43 732 998877',
        'eva.novak@example.com',
        (SELECT id FROM departments WHERE name = 'HR')
      );