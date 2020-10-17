

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NULL,
  `lastname` varchar(120) NOT NULL,
  `password` varchar(120)  NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY  (`id`),
  INDEX `customer_idx` (`id` ASC)
);


insert into customer (name,lastname,password) values('name1','lastname1','abc123');
insert into customer (name,lastname,password) values('name2','lastname2','abc123');
insert into customer (name,lastname,password) values('name3','lastname3','abc123');