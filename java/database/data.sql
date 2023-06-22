BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- Museums
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The Museum of Modern Art', 'OUPyV2WKYETuprScXSAtuA', 40.761441, -73.977625, '11 West 53rd St, New York, NY 10019', 'Museum', 'Come visit the Museum of Modern Art', true,'https://s3-media1.fl.yelpcdn.com/bphoto/ZH1CKc2eiHihovFfww-WMw/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('American Museum of Natural History', 'IH0vUeLREFC1D_TuNXge5w', 40.7813838,-73.973994,'200 Central Park W, New York, NY 10024', 'Museum', 'Come visit the Museum of Natural History', true,'https://s3-media4.fl.yelpcdn.com/bphoto/ZesAtfqK1ojCwr1WeIQE3w/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Whitney Museum of American Art','uVOgbsNrcVRip7uBaCWhBA', 40.7396059,-74.0089716,'99 Gansevoort St, New York, NY 10014', 'Museum', 'Come visit The Whitney', true,'https://s3-media2.fl.yelpcdn.com/bphoto/36Nm7jI5hYXLxLRTgj9JqA/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Guggenheim Museum', 'jv0Gu4wXNuyaoJMBOXqfVQ', 40.78301,-73.95912,'1071 5th Ave, New York, NY 10128', 'Museum', 'Come visit the Guggenheim', true,'https://s3-media1.fl.yelpcdn.com/bphoto/qqZVaYWzXZ8eLJXljssOTQ/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('National September 11 Memorial Museum', 'kViIWJFfAfWPpJOwAXBKGA',40.7114217,-74.0124765,'180 Greenwich St, New York, NY 10007', 'Museum', 'Come visit the National 9/11 Memorial', true,'https://s3-media4.fl.yelpcdn.com/bphoto/p2t5PSNsPNvdrZosA4K6iQ/o.jpg');

-- Parks
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The High Line', 'JION8hhg7q6zyayHYwhxIw',40.7396039,-74.00847657515718,'820 Washington St, New York, NY 10014', 'Park', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/8dWtYbMkHKNgyKe5S1DZkA/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Washington Square Park', 'bJQZKARP-Xn2AEqNNMe9OA',40.7296921,-73.9832969941198,'1 Washington Sq E, New York, NY 10003', 'Park', '', true,'https://s3-media3.fl.yelpcdn.com/bphoto/fa4gYfZ7qhXe18veP-OFpg/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Bryant Park', 'MJJi_5tGkWYI1VReTjhCCA',40.7536079618246,-74.0124765,'41 W 40th St, New York, NY 10018', 'Park', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/nqr2EH5zdzWLRcs-QBwg1g/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Central Park', 'KQ1iquXbdlUuxYJmjtBCug',40.78122724167864,-73.96651581384843,'1251 5th Ave, New York, NY 10029', 'Park', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/QIlDTICzAtMBrs3jfLzgTw/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Columbus Park', 'h5NhONSdSSXdaQc2IYPjjQ',40.715666,-73.999997,'67 Mulberry St, New York, NY 10013', 'Park', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/KOYqbBaqVFF8PQ5GDYI7kQ/o.jpg');

-- Nightlife
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The Woo Woo', 'GJ3vgPvcJlOejZepOotQug',40.76011384643282,-73.98724024445688,'266 W 47th St, New York, NY 10036', 'Nightlife', '', true,'https://s3-media1.fl.yelpcdn.com/bphoto/sbDnheawLAkqCk8L9qJfGA/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Deluxx Fluxx', 'bexFVBNOybB5E-3YUJuVmQ',40.7317892,-73.9891275,'125 E 11th St, New York, NY 10003', 'Nightlife', '', true,'https://s3-media4.fl.yelpcdn.com/bphoto/thbNY0MeqL7CK7pn9Hd4PQ/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Slate NY', 'OKO2WCseGqEBwj_obKETHA',40.741233515243344,-73.99319539999999,'54 W 21st St, New York, NY 10010', 'Nightlife', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/Fxl3IFh3HcWwACgE3MUUEA/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The Up & Up', 'zWrZBXj5F2V_0UknnTfqbw',40.7295104447158,-74.0006693438721,'116 MacDougal St, New York, NY 10012', 'Nightlife', '', true,'https://s3-media4.fl.yelpcdn.com/bphoto/a9VwUxa7jcPwcaeUcgf_bw/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Rooftop 93', '511OTzlr0FkL4esfBxMzIw',40.71705,-73.99522,'93 Bowery, New York, NY 10002', 'Nightlife', '', true,'https://s3-media3.fl.yelpcdn.com/bphoto/P_QP4qcPZvmwkssGW0kg-g/o.jpg');

-- Shopping
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Time Out Market New York', 'MGd6HFEq1ALD58XWNviSXw',40.70342863348067,-73.99214637055226,'55 Water St", Brooklyn, NY 11201', 'Shopping', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/L2-6QW46v5aZFpfAD2SK1A/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The Shops & Restaurants at Hudson Yards', 'YDG2BIuA1De-WkiH7WL53w',40.7533572,-74.0002527,'20 Hudson Yards, New York, NY 10001', 'Shopping', '', true,'https://s3-media4.fl.yelpcdn.com/bphoto/DlzeKK65Z84-cXr-0RcwvQ/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Chelsea Market', 'jF5CrF1BmvhoPlMTp5AoJQ',40.7419711362098,-74.0048450364568,'75 9th Ave, New York, NY 10011', 'Shopping', '', true,'https://s3-media3.fl.yelpcdn.com/bphoto/Cc9HPQN5OA9FSUe-aLgBiA/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('The Mysterious Bookshop', 'BmXjqgDma-tsJ5MpGcOIYQ',40.7149223,-74.0093093,'58 Warren St, New York, NY 10007', 'Shopping', '', true,'https://s3-media4.fl.yelpcdn.com/bphoto/xi8yyRSdJeyVsWV1C_O3kQ/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Grand Bazaar NYC', 'mvJI3Y5zveSOvL2Wjkm0jg',40.7806670752076,-73.9763787150665,'100 West 77th St, New York, NY 10024', 'Shopping', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/YSc8CanF0eGopBnPZqTvig/o.jpg');

-- Restaurants
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Daily Provisions', 't4M1VX4n34eEgUWMJ7cgKw',40.73768,-73.9876685,'103 E 19th St, New York, NY 10003', 'Restaurants', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/S7cAxsK3uFXMHRG0K-337g/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Lillo Cucina Italiana', 'qgY41g_eg0eNzewCXmKcaA',40.69023,-73.99634,'331 Henry St, Brooklyn, NY 11201', 'Restaurants', '', true,'https://s3-media2.fl.yelpcdn.com/bphoto/pXCk0KPUImmnM4pgEsBbUg/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Saigon Social', 'aiePDScoKCp5jbQPJXNtFw',40.721476,-73.988248,'172 Orchard St, New York, NY 10002', 'Restaurants', '', true,'https://s3-media1.fl.yelpcdn.com/bphoto/8Sc_cotpMvAEiTWfmIZ3sw/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('While We Were Young', 'YSflX6shqcP3zYno0Zz75Q',40.73437,-74.00272,'183 W 10th St, New York, NY 10014', 'Restaurants', '', true,'https://s3-media3.fl.yelpcdn.com/bphoto/I5QdRi1JuxzSIqgYJML8dg/o.jpg');
INSERT INTO location (name, business_id, lat, long, address, category, description, featured, image_url) VALUES('Celestine', '4aF0F8w7yXX9o5_QFky_ig',40.704676,-73.987975,'1 John St, Brooklyn, NY 11201', 'Restaurants', '', true,'https://s3-media3.fl.yelpcdn.com/bphoto/p98BTWB9IIb4RQyDQw13ZQ/o.jpg');


/* AWARDS */
INSERT INTO award (name, description) VALUES ('Curator','Visit all featured museums in NYC');
INSERT INTO award (name, description) VALUES ('Nature Lover','Visit all featured parks in NYC');
INSERT INTO award (name, description) VALUES ('Party Animal','Visit all featured nightlife attractions in NYC');
INSERT INTO award (name, description) VALUES ('Shopaholic','Visit all featured shopping sites in NYC');
INSERT INTO award (name, description) VALUES ('Foodie','Visit all featured restaurants in NYC');
INSERT INTO award (name, description) VALUES ('Excelsior','Visit all featured locations in NYC');

COMMIT TRANSACTION;
