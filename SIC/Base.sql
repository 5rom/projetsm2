CREATE TABLE PRODUIT (Pnum number PRIMARY KEY, Pnom varchar(64));
CREATE TABLE COMPOSITION (Pmajeur number references PRODUIT(Pnum), Pmineur number references PRODUIT(Pnum));
ALTER TABLE COMPOSITION ADD constraint PK_COMPOSITION PRIMARY KEY (Pmajeur,  Pmineur);

INSERT INTO PRODUIT (Pnum, Pnom) VALUES (1,"Stylo");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (2,"Capuchon");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (3,"Corps");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (4,"Tube");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (5,"Recharge");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (6,"Bouchon");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (7,"T�te d'�criture");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (8,"R�servoir");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (9,"Support de t�te");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (10,"T�te");
INSERT INTO PRODUIT (Pnum, Pnom) VALUES (11,"Bille");