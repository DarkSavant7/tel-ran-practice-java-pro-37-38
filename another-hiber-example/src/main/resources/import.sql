DROP TABLE  IF EXISTS proba.employees;
CREATE TABLE IF NOT EXISTS proba.employees (id int AUTO_INCREMENT, surname varchar(255), first_name VARCHAR(255), department varchar(255), salary int,  PRIMARY KEY (id));
INSERT INTO proba.employees (first_name, surname, department, salary) VALUES ('Bob', 'Bobs', 'build', 80), ('Jack', 'Jacks', 'explore', 80), ('John', 'Johns', 'test', 80);
