insert into categories(id,description,name,image_url) values
					('622da2a8-85ea-11ea-bc55-0242ac130003','Comidas esenciales del día','Ensaladas','categoria_ensalada'),
					('622da4ce-85ea-11ea-bc55-0242ac130003','Paquetes de ofertas','bundle','categoria_bundle'),
					('82de0b1f-900c-41f7-8de4-3a64bec30b00','Almuerzos basados en pastas','Pastas','categoria_pasta'),
					('7d6fd4c6-f896-414c-9111-9d8891cb486e','Almuerzos basados en menestras','Menestras','categoria_menestra'),
					('51255174-9a49-4857-bc9d-24267d8a12de','Almuerzos basados en tortillas','Tortillas','categoria_tortilla'),
					('d1b280ac-3204-477a-8520-106bc8ef2908','Almuerzos basados en pescados','Pescados','categoria_pescado'),
					('563637c3-cb11-4524-9fc8-d083915ccc0c','Bebidas refrescantes','Bebidas','categoria_bebida');
insert into sale_time_spans(id,description,ending_hour,starting_hour) values
					('cc43f5d7-8544-4a1e-913f-bf48da7feb11','Se rige al tiempo de apertura del local',null,null),
					('31cfeb32-69bc-41c3-8fcc-08bf78584dde','Horario de menus diarios','12:00:00','15:00:00');

insert into products(id,description,listed,name,price,sale_time_span_id,image_url) values
					('ba461cd9-9353-45c8-ad91-320492ca709a','Menu del día - 1',true,'Menú Diario',8.00,'31cfeb32-69bc-41c3-8fcc-08bf78584dde','producto_menu_diario'),
					('dc9f8bbd-daf2-4b3d-bccb-3c32e02ca9f7','Una deliciosa combinación de palta y atún.',true,'Palta con relleno de atún',10,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_palta_atun'),
					('7358aaaf-10a7-47b4-a3d7-2795c799b01a','Una combinación de calabaza con palta, tomate y pepino',true,'Ensalada de calabaza y verduras',10,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_ensalada_cruda'),
					('d1dc430b-527f-4fd6-9881-25b5d708af58','Una combinación simple pero deliciosa',true,'Ensalada de brócoli, zanahoria y nueces',10,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_ensalada_brocoli'),
					('f7565e03-7a3d-40ec-8b84-5309307c97d2','Plato a base de espaguetti y champiñones',true,'Espaguetti revuelto en champiñones',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_espaguetti_seta'),
					('d15562db-8bd0-4cc0-a1ea-c2d32e9325ba','Espaguetti con salsa blanca ',true,'Espaguetti con salsa blanca',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_espaguetti_salsablanca'),
					('e59115a6-f65a-410f-a412-a36da63ead2e','Plato a base de lentejas y ensalada',true,'Lentejas con ensalada cocida',10,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_lenteja_ensalda'),
					('a8311dc2-6aab-4a5d-acd9-727d8f6592a9','Plato de garbanzo con palta',true,'Garbanzo con palta',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_garbanzo_palta'),
					('84663571-16ff-4c92-bbe4-67363f014703','Tortilla de pollo y verduras',true,'Tortilla de pollo y verduras',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_tortilla_pollo_verduras'),
					('16d49d12-a5d5-4bd4-be4b-5a67379b5e8a','Tortilla de atún',true,'Tortilla de atún',10,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_tortilla_atun'),
					('56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5','Pescado asado con verduras',true,'Pescado asado',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_pescado_asado'),
					('73f44359-8a17-4d34-823e-ea29cc208f25','Pescado ahumado',true,'Pescado ahumado',12,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_pescado_ahumado'),
					('05099c08-f7f4-4f05-839f-e9e991fb212e','Infusión fría',true,'Infusión Té verde',3,'cc43f5d7-8544-4a1e-913f-bf48da7feb11','producto_bebida_infusion');

insert into stores(id,description,latitude,longitude) values
					('dcd63597-349a-41cf-ab46-0c81a882bb37','Tienda 1 - San Miguel','-12.077758508923393','-77.09322961974269'),
					('f0568d7d-c690-42ba-a274-db7e93661a50','Tienda 2 - San Miguel','-12.075101915038845','-77.07959002634568');
insert into combos(id,from_date,to_date) values
					('ba461cd9-9353-45c8-ad91-320492ca709a','2020-05-08','2020-05-08');

insert into meal_groups(id,name,combo_id, optional) values
					('daf3d3bd-1e93-4dcb-99f0-c15619e5d159','Segundo','ba461cd9-9353-45c8-ad91-320492ca709a', false),
					('2b8fbef8-4ff0-48b3-9a82-3d0570127971','Bebida','ba461cd9-9353-45c8-ad91-320492ca709a', false);
insert into meals(id,calories_in_kcal,ingredients) values
					('dc9f8bbd-daf2-4b3d-bccb-3c32e02ca9f7',1000,'Palta, Atún'),
					('7358aaaf-10a7-47b4-a3d7-2795c799b01a',500,'Calabaza, palta, tomate, pepino'),
					('d1dc430b-527f-4fd6-9881-25b5d708af58',400,'Brocoli, lechuga'),
					('f7565e03-7a3d-40ec-8b84-5309307c97d2',1500,'Espaguetti, champiñón'),
					('d15562db-8bd0-4cc0-a1ea-c2d32e9325ba',1500,'Espaguetti, mantequilla light'),
					('e59115a6-f65a-410f-a412-a36da63ead2e',1200,'Lentejas, lechuga'),
					('a8311dc2-6aab-4a5d-acd9-727d8f6592a9',1400,'Garbanzo'),
					('84663571-16ff-4c92-bbe4-67363f014703',1500,'Huevo, pollo, brocoli'),
					('16d49d12-a5d5-4bd4-be4b-5a67379b5e8a',1350,'Atun, huevo'),
					('56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5',1600,'Pescado, beterraga'),
					('73f44359-8a17-4d34-823e-ea29cc208f25',1500,'Pescado'),
					('05099c08-f7f4-4f05-839f-e9e991fb212e',400,'Infusion te verde');
insert into category_products(category_id,product_id) values
							('622da2a8-85ea-11ea-bc55-0242ac130003','dc9f8bbd-daf2-4b3d-bccb-3c32e02ca9f7'),
							('622da2a8-85ea-11ea-bc55-0242ac130003','7358aaaf-10a7-47b4-a3d7-2795c799b01a'),
							('622da2a8-85ea-11ea-bc55-0242ac130003','d1dc430b-527f-4fd6-9881-25b5d708af58'),
							('82de0b1f-900c-41f7-8de4-3a64bec30b00','f7565e03-7a3d-40ec-8b84-5309307c97d2'),
							('82de0b1f-900c-41f7-8de4-3a64bec30b00','d15562db-8bd0-4cc0-a1ea-c2d32e9325ba'),
							('7d6fd4c6-f896-414c-9111-9d8891cb486e','e59115a6-f65a-410f-a412-a36da63ead2e'),
							('7d6fd4c6-f896-414c-9111-9d8891cb486e','a8311dc2-6aab-4a5d-acd9-727d8f6592a9'),
							('51255174-9a49-4857-bc9d-24267d8a12de','84663571-16ff-4c92-bbe4-67363f014703'),
							('51255174-9a49-4857-bc9d-24267d8a12de','16d49d12-a5d5-4bd4-be4b-5a67379b5e8a'),
							('d1b280ac-3204-477a-8520-106bc8ef2908','56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5'),
							('d1b280ac-3204-477a-8520-106bc8ef2908','73f44359-8a17-4d34-823e-ea29cc208f25'),
							('563637c3-cb11-4524-9fc8-d083915ccc0c','05099c08-f7f4-4f05-839f-e9e991fb212e'),
							('622da4ce-85ea-11ea-bc55-0242ac130003','ba461cd9-9353-45c8-ad91-320492ca709a');
insert into combo_group_meals(group_id,meal_id) values
							('daf3d3bd-1e93-4dcb-99f0-c15619e5d159','f7565e03-7a3d-40ec-8b84-5309307c97d2'),
							('daf3d3bd-1e93-4dcb-99f0-c15619e5d159','84663571-16ff-4c92-bbe4-67363f014703'),
							('daf3d3bd-1e93-4dcb-99f0-c15619e5d159','7358aaaf-10a7-47b4-a3d7-2795c799b01a'),
							('daf3d3bd-1e93-4dcb-99f0-c15619e5d159','56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5'),
							('2b8fbef8-4ff0-48b3-9a82-3d0570127971','05099c08-f7f4-4f05-839f-e9e991fb212e');

insert into roles(id, description,name) values
                		(1, 'Rol con privilegios de administrador','Administrador'),
				        (2,'Rol exclusivo para el uso del app de delivery como cliente','Cliente'),
				        (3,'Rol para el mantenimiento de datos de los platos','Gerente'),
						(4,'Rol para el encargado de realizar el delivery','Courier');


insert into users(id, email, email_validated, first_name, last_name, last_access_on, password, registered_on, status_code, role_id) values
				('366b5071-9eaf-4356-9ccf-9fe5b5a4d2c8','admin@user.com', true, 'Administrator', null, '20200101', '$2y$10$uIgID8e4NIKuddGCHLeCM.u7fhw7O7oALKcEmQD1XaaJ1ikTNL0gW', '20200101', 0, 1);

insert into stocks(product_id,store_id,in_stock,restocked_on) values
				  ('ba461cd9-9353-45c8-ad91-320492ca709a','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('dc9f8bbd-daf2-4b3d-bccb-3c32e02ca9f7','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('7358aaaf-10a7-47b4-a3d7-2795c799b01a','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('d1dc430b-527f-4fd6-9881-25b5d708af58','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('f7565e03-7a3d-40ec-8b84-5309307c97d2','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('d15562db-8bd0-4cc0-a1ea-c2d32e9325ba','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('e59115a6-f65a-410f-a412-a36da63ead2e','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('a8311dc2-6aab-4a5d-acd9-727d8f6592a9','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('84663571-16ff-4c92-bbe4-67363f014703','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('16d49d12-a5d5-4bd4-be4b-5a67379b5e8a','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('73f44359-8a17-4d34-823e-ea29cc208f25','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),
				  ('05099c08-f7f4-4f05-839f-e9e991fb212e','dcd63597-349a-41cf-ab46-0c81a882bb37',true,'2020-04-26 00:00:00'),

				  ('ba461cd9-9353-45c8-ad91-320492ca709a','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('dc9f8bbd-daf2-4b3d-bccb-3c32e02ca9f7','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('7358aaaf-10a7-47b4-a3d7-2795c799b01a','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('d1dc430b-527f-4fd6-9881-25b5d708af58','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('f7565e03-7a3d-40ec-8b84-5309307c97d2','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('d15562db-8bd0-4cc0-a1ea-c2d32e9325ba','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('e59115a6-f65a-410f-a412-a36da63ead2e','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('a8311dc2-6aab-4a5d-acd9-727d8f6592a9','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('84663571-16ff-4c92-bbe4-67363f014703','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('16d49d12-a5d5-4bd4-be4b-5a67379b5e8a','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('56a6b9d9-a0c6-4a43-9f03-5b4c65b02ae5','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('73f44359-8a17-4d34-823e-ea29cc208f25','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00'),
				  ('05099c08-f7f4-4f05-839f-e9e991fb212e','f0568d7d-c690-42ba-a274-db7e93661a50',true,'2020-04-26 00:00:00');
insert into tasks(id,code,name) values
				(1,1,'Preparando'),
				(2,2,'Enviando'),
				(3,3,'Esperando Pago');

insert into customers(id, user_id) values
				('3369cf8c-9a25-4355-a6cf-91a5355de96b',null),
				('8b7c06dc-1160-4256-a77c-3e0569ded556', null);