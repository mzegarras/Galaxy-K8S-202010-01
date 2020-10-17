

DELIMITER //

CREATE PROCEDURE GetAllCustomers()
BEGIN
    SELECT *  FROM customer;
END //



CREATE PROCEDURE GetAllCustomersById (IN id_customer INT)
BEGIN
    SELECT * FROM customer WHERE id = id_customer;
END //

CREATE PROCEDURE CustomersInsert (IN p_name varchar(120),
                            IN p_lastname varchar(120),
                            IN p_password varchar(120))
BEGIN
    insert into customer (name,lastname,password) values(p_name,p_lastname,p_password);
END //

CREATE PROCEDURE CustomersUpdate (IN p_id_customer INT,
                            IN p_name varchar(120),
                            IN p_lastname varchar(120),
                            IN p_password varchar(120))
BEGIN
    update customer set name=p_name,lastname=p_lastname,password=p_password
    where id = p_id_customer;
END //

CREATE PROCEDURE CustomersDelete (IN p_id_customer INT)
BEGIN
    delete from customer where id = p_id_customer;
END //

DELIMITER ;

