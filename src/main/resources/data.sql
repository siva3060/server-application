/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01', false, 0);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02', false, 0);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03', false, 0);


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04', false, 0);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05', true, 5);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected,car_id) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06', false, 0);

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username,car_selected,car_id)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07',false, 0);

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username,car_selected,car_id)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE', 'driver08pw', 'driver08', false, 0);

-- Create 3 Cars
insert into car (id, date_created, license_plate, seat_count, is_convertible, rating, engine_type, make, model, is_avaliable, booked_by)
values (1, now(), 'KA51E342', 4, false, 4.2,'ELECTRIC','Tesla','Model X', true,0);
insert into car (id, date_created, license_plate, seat_count, is_convertible, rating, engine_type, make, model, is_avaliable, booked_by)
values (2, now(), 'AP01E23', 2, true, 4.5,'HYBRID','FORD','e-pickup', true,0);
insert into car (id, date_created, license_plate, seat_count, is_convertible, rating, engine_type, make, model, is_avaliable, booked_by)
values (3, now(), 'KL01D898', 8, false, 3.2,'GAS','Honda','Accord', true,0);

-- Create 1 Car Which is Booked
insert into car (id, date_created, license_plate, seat_count, is_convertible, rating, engine_type, make, model, is_avaliable, booked_by)
values (5, now(), 'AP39ADG', 8, false, 3.2,'GAS','Merc','CD550', false,5);
