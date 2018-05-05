/*
TWO DEFAULT VALUES ON (PLAyer)
*/

CREATE TABLE Guild
(
Name		CHAR(25)		NOT NULL,	
Association VARCHAR(10)		NOT NULL	
			CHECK( (Association IN ('Merchant','Hunter','Thief')) AND Association <> ''),
Level		INT				NOT NULL	CHECK(Level > -1),
Rank		INT				CONSTRAINT RankTable CHECK( (Rank > 0) AND (Rank < 10000) ),
PRIMARY KEY	(Name)
);


CREATE TABLE Player
(
Username	CHAR(25)		NOT NULL,	
Level		SMALLINT		NOT NULL	DEFAULT 1	CHECK(Level > 0),
Power		INT				NOT NULL	CHECK(Power > 100),
Guild		CHAR(25)		DEFAULT '',		
PRIMARY KEY	(Username),
FOREIGN KEY	(Guild)			REFERENCES Guild ON DELETE SET NULL
);

CREATE TABLE Equipment
(
Id			INTEGER			NOT NULL,
TotalDamage	INT				NOT NULL	CHECK(TotalDamage > -1),
Accuracy	REAL			NOT NULL	CHECK( (Accuracy >= 0) AND (Accuracy <= 100) ),
TotalProtection INT			NOT NULL	CHECK(TotalProtection > -1),
ParryRate	REAL			NOT	NULL	CHECK( (ParryRate >= 0) AND (ParryRate <= 100)),
Player		CHAR(25)		NOT NULL,
PRIMARY KEY	(Id),
FOREIGN KEY (Player)		REFERENCES Player ON DELETE CASCADE
);

CREATE TABLE Inventory
(
Id			INTEGER 		NOT NULL,
Gold		INT 			NOT NULL	CONSTRAINT MaxGold CHECK( (Gold > -1) AND (Gold < 1000000) ),
Player		CHAR(25)		NOT NULL,
PRIMARY KEY	(Id),
FOREIGN KEY (Player)		REFERENCES Player ON DELETE CASCADE
);

CREATE TABLE Material
(
Id			INTEGER 		NOT NULL,
Name		VARCHAR(20)		NOT NULL	CHECK(Name <> ''),
Quantity	SMALLINT		NOT NULL	CONSTRAINT MaxQuantity CHECK( (Quantity > 0) AND (Quantity < 1000) ),
Inventory	INT 			NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (Inventory)		REFERENCES Inventory ON DELETE CASCADE
);

CREATE TABLE Weapon
(
Id			INTEGER 		NOT NULL,
Name		VARCHAR(30)		NOT NULL	CHECK(Name <> ''),
Damage 		INT 			NOT NULL	CHECK(Damage > -1),
Accuracy	REAL			NOT NULL	CHECK( (Accuracy >= 0) AND (Accuracy <= 100) ),
Inventory	INT,
Equipment	INT,
CONSTRAINT FloatingEntity	CHECK ( (Inventory IS NOT NULL) OR (Equipment IS NOT NULL) ),
CONSTRAINT TwoPlaces		CHECK ( Inventory <> Equipment),
PRIMARY KEY (Id),
FOREIGN KEY (Inventory)		REFERENCES Inventory ON DELETE SET NULL,
FOREIGN KEY (Equipment)		REFERENCES Equipment ON DELETE SET NULL
);

CREATE TABLE Armor
(
Id			INTEGER 		NOT NULL,
Name		VARCHAR(30)		NOT NULL	CHECK(Name <> ''),
Protection  INT 			NOT NULL	CHECK(Protection > -1),
ParryRate	REAL			NOT NULL	CHECK( (ParryRate >= 0) AND (ParryRate <= 40)),
ArmorPart	VARCHAR(6)		NOT NULL	
							CHECK( (ArmorPart IN ('Head', 'Chest', 'Legs')) AND (ArmorPart <> '') ),
ArmorType	VARCHAR(6)		NOT NULL
							CHECK( (ArmorType IN ('Heavy', 'Light', 'Robe')) AND (ArmorType <> '') ),
Inventory	INT,
Equipment	INT,
CONSTRAINT FloatingEntity 	CHECK ( (Inventory IS NOT NULL) OR (Equipment IS NOT NULL) ),
CONSTRAINT TwoPlaces		CHECK ( Inventory <> Equipment),
PRIMARY KEY (Id),
FOREIGN KEY (Inventory)		REFERENCES Inventory ON DELETE SET NULL,
FOREIGN KEY (Equipment)		REFERENCES Equipment ON DELETE SET NULL
);

