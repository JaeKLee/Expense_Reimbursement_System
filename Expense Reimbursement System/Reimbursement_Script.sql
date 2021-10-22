
--Create tables
--lookup tables
CREATE TABLE ERS_REIMBURSEMENT_STATUS(
	REIMB_STATUS_ID SERIAL PRIMARY KEY
	, REIMB_STATUS VARCHAR(10) NOT NULL
);

CREATE TABLE ERS_REIMBURSEMENT_TYPE(
	REIMB_TYPE_ID SERIAL PRIMARY KEY
	, REIMB_TYPE VARCHAR(10) NOT NULL
);

CREATE TABLE ERS_USER_ROLES(
	ERS_USER_ROLE_ID SERIAL PRIMARY KEY
	, USER_ROLE VARCHAR(10) NOT NULL
);


--not lookup tables
CREATE TABLE ERS_REIMBURSEMENT(
	REIMB_ID SERIAL PRIMARY KEY
	, REIMB_AMOUNT NUMERIC NOT NULL
	, REIMB_SUBMITTED timestamp 
	, REIMB_RESOLVED timestamp
	, REIMB_DESCRIPTION VARCHAR(250)
	, REIMB_AUTHOR INTEGER NOT NULL
	, REIMB_RESOLVER INTEGER
	, REIMB_STATUS_ID INTEGER NOT NULL
	, REIMB_TYPE_ID INTEGER NOT NULL
);

CREATE TABLE ERS_USERS(
	ERS_USERS_ID SERIAL PRIMARY KEY
	, ERS_USERNAME VARCHAR(50) NOT NULL UNIQUE
	, ERS_PASSWORD VARCHAR(50) NOT NULL
	, USER_FRIST_NAME VARCHAR(100) NOT NULL
	, USER_LAST_NAME VARCHAR(100) NOT NULL
	, USER_EMAIL VARCHAR(150) NOT NULL UNIQUE
	, USER_ROLE_ID INTEGER NOT NULL
);

--Create Views
CREATE VIEW ers_user_view AS
SELECT * FROM ers_users eu INNER JOIN ers_user_roles eur 
ON eu.user_role_id = eur.ers_user_role_id ;

CREATE VIEW ers_reimb_view AS
SELECT 	    er.* 
			, eu.user_frist_name AS ers_resolver_first_name
			, eu.user_last_name AS ers_resolver_last_name
			, eu2.user_frist_name AS ers_author_first_name
			, eu2.user_last_name AS ers_author_last_name
			, ert.reimb_type AS ers_type
			, ers.reimb_status AS ers_status
FROM 	    ERS_REIMBURSEMENT er 
INNER JOIN  ERS_REIMBURSEMENT_STATUS ers ON er.reimb_status_id = ers.reimb_status_id 
INNER JOIN  ERS_REIMBURSEMENT_TYPE ert   ON er.reimb_type_id   = ert.reimb_type_id 
LEFT  JOIN  ers_users eu /*resolver*/	 ON er.reimb_resolver  = eu.ers_users_id 
LEFT  JOIN  ers_users eu2 /*author*/	 ON er.reimb_author    = eu2.ers_users_id ;


--ALTER TABLES (ADD)
ALTER TABLE ers_reimbursement ADD FOREIGN KEY (REIMB_STATUS_ID) REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID);
ALTER TABLE ers_reimbursement ADD FOREIGN KEY (REIMB_TYPE_ID) REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID);
ALTER TABLE ers_reimbursement ADD FOREIGN KEY (REIMB_AUTHOR) REFERENCES ERS_USERS(ERS_USERS_ID);
ALTER TABLE ers_reimbursement ADD FOREIGN KEY (REIMB_RESOLVER) REFERENCES ERS_USERS(ERS_USERS_ID);
ALTER TABLE ERS_USERS ADD FOREIGN KEY (USER_ROLE_ID) REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID);


--selects
SELECT * FROM ers_reimbursement;
SELECT * FROM ers_reimbursement_type;
SELECT * FROM ers_reimbursement_status;
SELECT * FROM ers_users;
SELECT * FROM ers_user_roles;
SELECT * FROM ers_user_view;
SELECT * FROM ers_reimb_view ORDER BY reimb_id ;
SELECT * FROM ers_reimb_view WHERE ers_author_username = 'super' ORDER BY ers_status DESC, reimb_id ASC ;
SELECT * FROM ers_reimb_view WHERE ers_author_first_name = 'Kungfu' ORDER BY ers_status DESC, reimb_id ASC ;

--Inserts
INSERT INTO ers_user_roles (user_role) VALUES('Manager');
INSERT INTO ers_user_roles (user_role) VALUES('Employee');

INSERT INTO ers_reimbursement_status (reimb_status) VALUES('Approved');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES('Denied');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES('Pending');

INSERT INTO ers_reimbursement_type (reimb_type) VALUES('Supply');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES('Trip');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES('Misc');


--ers_users
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('super', 	  'duper', 		'Markiplier', 	 'Kirby', 		 'markiplier@youatest.com', 1);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('Kirby', 'smashdembros', 'Masahiro', 'Sakurai', 'kirbyisthebest@youatest.com', 1);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('bichon', 'frise', 'Ash', 'Pokemon', 'ashneedstoleave@youatest.com', 2);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('yoru', 'night', 'Bam', '25th', '25thofbam@youatest.com', 2);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('duper', 'super', 'TestFName', 'TestLName', 'dupersuper@youatest.com', 2);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('grapejuice', 'panda', 'Kungfu', 'Panda', 'kungfupanda@youatest.com', 2);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('pho', 'itsphonotpo', 'bear', 'genshin', 'genshinbear@youatest.com', 2);
INSERT INTO ers_users ( ers_username, ers_password, user_frist_name, user_last_name, user_email, user_role_id)
				VALUES('pop', 'movies', 'Raiden', 'Shogun', 'raidenshogun@youatest.com', 2);

--ers_reimbursement			
INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID  ) 
					   VALUES (100, now(), 3, 1, 1 );
INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID  ) 
					   VALUES (128, 		 now(), 		  5, 			3, 				 1 );
INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID  ) 
					   VALUES (123.456, 	 now(), 		  5, 			3, 				 1 );

INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, reimb_description, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) 
						VALUES(293.432, now(), 'This is a test2', 4, 1, 2 );
INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, reimb_description, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) 
						VALUES(1203, now(), 'Business trip to NYC', 6, 1, 2 );


INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, reimb_description, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) 
					 	VALUES(1139.48, now(), 'Family vacation to Barcelona', 4, 3, 2 );

INSERT INTO ers_reimb_view (REIMB_AMOUNT, REIMB_SUBMITTED, ers_author_first_name, ers_author_last_name, reimb_description, ers_type, ers_status)
					 VALUES(112.392, now(), 'Kungfu', 'Panda', 'adding to view tabla', 'Supply', 'Pending' );

		





