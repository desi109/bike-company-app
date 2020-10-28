INSERT INTO bike (id, contact, email, model, name, phone, purchase_date, purchase_price)
  VALUES (1, TRUE, 'jeff@bikes.com', 'Globo MTB 29 Full Suspension', 'Jeff Miller', '328-443-5555', '15-01-2020', '1100');
INSERT INTO bike (id, contact, email, model, name, phone, purchase_date, purchase_price)
  VALUES (2, FALSE, 'samantha@bikes.com', 'Globo Carbon Fiber Race Series', 'Samantha Davis', '448-397-5555', '19-03-2020', '1999');
INSERT INTO bike (id, contact, email, model, name, phone, purchase_date, purchase_price)
  VALUES (3, TRUE, 'dave@bikes.com', 'Globo Time Trial Blade', 'Dave Warren', '563-891-5555', '29-09-2020', '2100');

INSERT INTO hibernate_sequence (next_val) 
  VALUES (4);
