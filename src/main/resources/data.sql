insert into PRODUCT values (1,'Glue Tube','Glue in a tube', NULL );
insert into PRODUCT values (2,'Glue can', 'Glue in a can',1);
insert into PRODUCT values (3,'Crackers', 'Negresco crackers', NULL );

insert into IMAGE(ID, TYPE, PRODUCT_ID) values (1,'tube',1);
insert into IMAGE(ID, TYPE, PRODUCT_ID) values (2,'can', 2);
insert into IMAGE(ID, TYPE, PRODUCT_ID) values (3,'package',3);
insert into IMAGE(ID, TYPE, PRODUCT_ID) values (4,'blur',1);