INSERT INTO manufacturer (code,name) VALUES (1,'Manufacturer1');
INSERT INTO manufacturer (code,name) VALUES (2,'Manufacturer2');
INSERT INTO manufacturer (code,name) VALUES (3,'Manufacturer3');
INSERT INTO car (id,lat,lon,condition,body,model,manufacturer_code,number_of_doors,fuel_type,engine,mileage,model_year,production_year,external_color,created_at,modified_at) VALUES (1,'55','60','USED','body', 'model',1,4,'gasoil','engine',12,1994,2022,'red',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());