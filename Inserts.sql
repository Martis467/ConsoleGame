-- Guild

--							|Name|				|Association|	|Level|	|Rank|
INSERT INTO Guild VALUES ('TamsosValdovai', 	'Thief', 		57, 	5);
INSERT INTO Guild VALUES ('Daktarai', 			'Hunter', 		48, 	4);
INSERT INTO Guild VALUES ('LaidoRiteriai',	 	'Thief', 		67, 	3);
INSERT INTO Guild VALUES ('KaimoUkininkai', 	'Merchant', 	89, 	2);
INSERT INTO Guild VALUES ('MirtiesMedziotojai', 'Hunter', 		57, 	1);

--	Player

--							|Username|		|Level|	|Power|		|Guild|
INSERT INTO Player VALUES ('Bulvedoras',	34,		1150,	'Daktarai');
INSERT INTO Player VALUES ('Kiskiuxas',		15,		790,	'Daktarai');
INSERT INTO Player VALUES ('Sangokas',		68,		8650,	'Daktarai');

INSERT INTO Player VALUES ('Natasa',		14,		510,	'TamsosValdovai');
INSERT INTO Player VALUES ('PonasBomzas',	51,		5450,	'TamsosValdovai');
INSERT INTO Player VALUES ('Suktinukas',	57,		5380,	'TamsosValdovai');

INSERT INTO Player VALUES ('AgirdiBahuras',	68,		8550,	'LaidoRiteriai');
INSERT INTO Player VALUES ('KasYra',		79,		9666,	'LaidoRiteriai');
INSERT INTO Player VALUES ('DrugelisErelis',98,		14350,	'LaidoRiteriai');

INSERT INTO Player VALUES ('RamunasKarbauskis',	12,		510,	'KaimoUkininkai');
INSERT INTO Player VALUES ('MantasKarbauskis',	12,		490,	'KaimoUkininkai');
INSERT INTO Player VALUES ('JurgaKarbauskyte',	12,		480,	'KaimoUkininkai');

INSERT INTO Player VALUES ('MislingaSnaige',	71,		9199,	'MirtiesMedziotojai');
INSERT INTO Player VALUES ('ZveriaZmogis',		43,		4333,	'MirtiesMedziotojai');
INSERT INTO Player VALUES ('SiauboNaikintojas',	101,	17856,	'MirtiesMedziotojai');

-- Equipment

--							|Id|	|TotalDmg|	|Acc|	|TotalProt|	|Parr|	|Player|
INSERT INTO Equipment VALUES (1,	 600, 		54.83, 	860, 		37.8, 	'Bulvedoras');
INSERT INTO Equipment VALUES (2,	 480, 		44.02, 	550, 		39.9, 	'Kiskiuxas');
INSERT INTO Equipment VALUES (3,	 6330, 		72.83, 	8103, 		11.85, 	'Sangokas');

INSERT INTO Equipment VALUES (4,	 71, 		64.83, 	159, 		50.00, 	'Natasa');
INSERT INTO Equipment VALUES (5,	 2230, 		79.75, 	3150, 		68.45, 	'PonasBomzas');
INSERT INTO Equipment VALUES (6,	 4600, 		40.81, 	2150, 		46.8, 	'Suktinukas');

INSERT INTO Equipment VALUES (7,	 7950, 		73.81, 	2250, 		31.8, 	'AgirdiBahuras');
INSERT INTO Equipment VALUES (8,	 8000, 		66.35, 	4600, 		46.0, 	'KasYra');
INSERT INTO Equipment VALUES (9,	 8350, 		61.78, 	11003, 		49.8, 	'DrugelisErelis');

INSERT INTO Equipment VALUES (10,	 500, 		50.00, 	0, 			77.77, 	'RamunasKarbauskis');
INSERT INTO Equipment VALUES (11,	 500, 		50.00, 	0, 			77.77, 	'MantasKarbauskis');
INSERT INTO Equipment VALUES (12,	 500, 		50.00, 	0, 			77.77, 	'JurgaKarbauskyte');

INSERT INTO Equipment VALUES (13,	 6803, 		81.86, 	4115, 		81.7, 	'MislingaSnaige');
INSERT INTO Equipment VALUES (14,	 1205, 		81.03, 	2150, 		48.33, 	'ZveriaZmogis');
INSERT INTO Equipment VALUES (15,	 12350, 	59.00, 	12890, 		53.00, 	'SiauboNaikintojas');

--Inventory

--							|Id|	|Gold|	|Player|
INSERT INTO Inventory VALUES (1, 	1563, 	'Bulvedoras');
INSERT INTO Inventory VALUES (2, 	363, 	'Kiskiuxas');
INSERT INTO Inventory VALUES (3, 	79583, 	'Sangokas');

INSERT INTO Inventory VALUES (4, 	563, 	'Natasa');
INSERT INTO Inventory VALUES (5, 	51205, 	'PonasBomzas');
INSERT INTO Inventory VALUES (6, 	7868, 	'Suktinukas');

INSERT INTO Inventory VALUES (7, 	11385, 	'AgirdiBahuras');
INSERT INTO Inventory VALUES (8, 	89958,	'KasYra');
INSERT INTO Inventory VALUES (9, 	155398, 'DrugelisErelis');

INSERT INTO Inventory VALUES (10, 	500000, 'RamunasKarbauskis');
INSERT INTO Inventory VALUES (11, 	500000, 'MantasKarbauskis');
INSERT INTO Inventory VALUES (12, 	500000, 'JurgaKarbauskyte');

INSERT INTO Inventory VALUES (13, 	79853, 	'MislingaSnaige');
INSERT INTO Inventory VALUES (14, 	81236, 	'ZveriaZmogis');
INSERT INTO Inventory VALUES (15, 	398535, 'SiauboNaikintojas');


--Rmove ID here for automatic generation
--Material

--							|Id|	|Name|			|Quan|	|Inv|
--Bulvedoras
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Lobsteris', 		8, 		14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Bulves', 			41, 	1);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Malkos', 			21, 	1);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		21, 	1);

--Kiskiuxas
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Silke',			10,		2);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Akmenai', 			6, 		2);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaulai',			17,		2);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Herbas',			3,		2);

--Sangokas
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		51, 	3);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Nuodai',	 		6,	 	3);

--Natasa
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Melyni Dazai', 	51, 	4);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Plunksnos', 		51, 	4);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Akmenai', 			51, 	4);

--PonasBomzas
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Akmenai', 			71, 	5);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Paveikslas', 		2,	 	5);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Malkos', 			46, 	5);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		51, 	5);

--Suktinukas
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Herbas',			3,		6);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Raudoni Dazai',	51, 	6);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		34, 	6);

--AgirdiBahuras
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Akmenai', 			115, 	7);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Nuodai',	 		8,	 	7);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Malkos', 			99, 	7);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaukole', 			6,	 	7);

--KasYra
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Akmenai', 			169, 	8);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		168, 	8);

--DrugelisErelis
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		379, 	9);

--RamunasKarbauskis
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Bulves', 			999, 	10);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Morkos', 			999, 	10);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Ridikai', 			999, 	10);

--MantasKarbauskis
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Baravykai',  		999, 	11);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Lepsiai',  		999, 	11);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Musimires',  		999, 	11);

--JurgaKarbauskyte
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kopustas',  		999, 	12);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Burokas',  		999, 	12);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Svogunas',  		999, 	12);

--MislingaSnaige
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		154, 	13);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Nuodai',	 		24,	 	13);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Raudoni Dazai',	21, 	13);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Melyni Dazai', 	21, 	13);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Herbas',			30,		13);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Malkos', 			255, 	13);

--ZveriaZmogis
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Herbas',			36,		14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Melyni Dazai', 	17, 	14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Geltoni Dazai', 	17, 	14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Raudoni Dazai',	17, 	14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Zali Dazai',		17, 	14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaukole', 			101,	14);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaulai',			117,	14);

--SiauboNaikintojas
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Herbas',			76,		15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaulai',			197,	15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kaukole', 			271,	15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Malkos', 			461, 	15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Kristalai', 		458, 	15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Nuodai',	 		98,	 	15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Silke',			68,		15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Krabas',			49,		15);
INSERT INTO Material(Name, Quantity, Inventory) VALUES ('Raudoni Dazai',	75, 	15);


-- Weapon

--							|Id|	|Name|				|Damage|	|Accurasy|	|InvId|	|EquipId|
--Equiped
INSERT INTO Weapon VALUES (1,	'Metalinis Kardas',		459,		50.5,		NULL,	1);
INSERT INTO Weapon VALUES (2,	'Metalinis Durklas',	389,		46.5,		NULL,	2);
INSERT INTO Weapon VALUES (3,	'Felikso Ietis',		5126,		66.77,		NULL,	3);

INSERT INTO Weapon VALUES (4,	'Ragatke',				53,			64.01,		NULL,	4);
INSERT INTO Weapon VALUES (5,	'Tamsos Samtis',		1800,		72.81,		NULL,	5);
INSERT INTO Weapon VALUES (6,	'Mikelandzelo Kujis',	3800,		35.5,		NULL,	6);

INSERT INTO Weapon VALUES (7,	'Dziuhakos Kalavijas',	6456,		71.5,		NULL,	7);
INSERT INTO Weapon VALUES (8,	'Dziuhakos Kalavijas',	6456,		71.5,		NULL,	8);
INSERT INTO Weapon VALUES (9,	'Dziuhakos Durklas',	5946,		50.32,		NULL,	9);

INSERT INTO Weapon VALUES (10,	'Velnio Kardas',		6105,		63.46,		NULL,	13);
INSERT INTO Weapon VALUES (11,	'Azuolinis lankas',		6456,		76.5,		NULL,	14);
INSERT INTO Weapon VALUES (12,	'Siaubo Kirvis',		9579,		49.00,		NULL,	15);

--In Inventory
INSERT INTO Weapon VALUES (13,	'Metalinis Durklas',	389,		46.5,		1,	NULL);

INSERT INTO Weapon VALUES (14,	'Plieninis Durklas',	799,		49.32,		3,	NULL);
INSERT INTO Weapon VALUES (15,	'Medinis Kardas',		111,		35.5,		3,	NULL);
INSERT INTO Weapon VALUES (16,	'Plieninis Kirvis',		1003,		45.18,		3,	NULL);

INSERT INTO Weapon VALUES (17,	'Mitrilo Durklas',		1973,		56.3,		5,	NULL);
INSERT INTO Weapon VALUES (18,	'Burtininko Lazda',		1530,		11.31,		5,	NULL);
INSERT INTO Weapon VALUES (19,	'Arbaletas',			1460,		75.3,		5,	NULL);

INSERT INTO Weapon VALUES (20,	'Ragatke',				53,			64.01,		6,	NULL);

INSERT INTO Weapon VALUES (21,	'Mikelandzelo Kujis',	3800,		35.5,		7,	NULL);
INSERT INTO Weapon VALUES (22,	'Mikelandzelo Katana',	3560,		44.39,		7,	NULL);
INSERT INTO Weapon VALUES (23,	'Mikelandzelo Durklas',	3100,		49.79,		7,	NULL);

INSERT INTO Weapon VALUES (24,	'Metalinis Kardas',		459,		50.5,		8,	NULL);
INSERT INTO Weapon VALUES (25,	'Plieninis Kirvis',		1003,		45.18,		8,	NULL);

INSERT INTO Weapon VALUES (26,	'Arbaletas',			1460,		75.3,		9,	NULL);
INSERT INTO Weapon VALUES (27,	'Felikso Ietis',		5126,		66.77,		9,	NULL);
INSERT INTO Weapon VALUES (28,	'Burtininko Lazda',		1530,		11.31,		9,	NULL);
INSERT INTO Weapon VALUES (29,	'Velnio Durklas',		5360,		67.15,		9,	NULL);

INSERT INTO Weapon VALUES (30,	'Burtininko Lazda',		1530,		11.31,		13,	NULL);
INSERT INTO Weapon VALUES (31,	'Plieninis Kirvis',		1003,		45.18,		13,	NULL);
INSERT INTO Weapon VALUES (32,	'Felikso Ietis',		5126,		66.77,		13,	NULL);

INSERT INTO Weapon VALUES (33,	'Metalinis Durklas',	389,		46.5,		14,	NULL);
INSERT INTO Weapon VALUES (34,	'Ragatke',				53,			64.01,		14,	NULL);

INSERT INTO Weapon VALUES (35,	'Arbaletas',			1460,		75.3,		15,	NULL);
INSERT INTO Weapon VALUES (36,	'Velnio Kardas',		6105,		63.46,		15,	NULL);
INSERT INTO Weapon VALUES (37,	'Velnio Durklas',		5360,		67.15,		15,	NULL);
INSERT INTO Weapon VALUES (38,	'Burtininko Lazda',		1530,		11.31,		15,	NULL);
INSERT INTO Weapon VALUES (39,	'Mikelandzelo Katana',	3560,		44.39,		15,	NULL);
INSERT INTO Weapon VALUES (40,	'Velnio Durklas',		5360,		67.15,		15,	NULL);


--Armor

--						|Id|	|Name|					|Prot|	|Parry|	|Apart|	|Atype|		|Inv|	|Eqp|		
--Equiped
INSERT INTO Armor VALUES (1,	'Riterio Salmas',		134,	9.5,	'Head',	'Heavy',	NULL,	1);
INSERT INTO Armor VALUES (2,	'Riterio Kirasa',		374,	13.5,	'Chest','Heavy',	NULL,	1);
INSERT INTO Armor VALUES (3,	'Riterio Antslauniai',	210,	12.11,	'Legs',	'Heavy',	NULL,	1);

INSERT INTO Armor VALUES (4,	'Riterio Kirasa',		374,	14.5,	'Chest','Heavy',	NULL,	2);
INSERT INTO Armor VALUES (5,	'Riterio Antslauniai',	210,	12.11,	'Legs',	'Heavy',	NULL,	2);

INSERT INTO Armor VALUES (6,	'Drakono Salmas',		1756,	3.5,	'Head',	'Heavy',	NULL,	3);
INSERT INTO Armor VALUES (7,	'Drakono Kirasa',		3452,	2.42,	'Chest','Heavy',	NULL,	3);
INSERT INTO Armor VALUES (8,	'Drakono Antslauniai',	2439,	2.9,	'Legs',	'Heavy',	NULL,	3);

INSERT INTO Armor VALUES (9,	'Riterio Salmas',		134,	9.5,	'Head',	'Heavy',	NULL,	4);

INSERT INTO Armor VALUES (10,	'Mitrilo Salmas',		560,	24.41,	'Head',	'Heavy',	NULL,	5);
INSERT INTO Armor VALUES (11,	'Mitrilo Kirasa',		1690,	17.36,	'Chest','Light',	NULL,	5);
INSERT INTO Armor VALUES (12,	'Mitrilo Antslauniai',	1003,	20.76,	'Legs',	'Light',	NULL,	5);

INSERT INTO Armor VALUES (13,	'Dumbuldoro Kepure',	375,	16.76,	'Head',	'Robe',		NULL,	6);
INSERT INTO Armor VALUES (14,	'Dumbuldoro Apsiaustas',980,	17.27,	'Chest','Robe',		NULL,	6);
INSERT INTO Armor VALUES (15,	'Dumbuldoro Sijonas',	670,	18.45,	'Head','Robe',		NULL,	6);

INSERT INTO Armor VALUES (16,	'Felikso Salmas',		470,	12.86,	'Head',	'Heavy',	NULL,	7);
INSERT INTO Armor VALUES (17,	'Felikso Kirasa',		890,	9.14,	'Chest','Heavy',	NULL,	7);
INSERT INTO Armor VALUES (18,	'Felikso Antslauniai',	600,	10.21,	'Legs',	'Heavy',	NULL,	7);

INSERT INTO Armor VALUES (19,	'Nigaros Salmas',		780,	16.66,	'Head',	'Light',	NULL,	8);
INSERT INTO Armor VALUES (20,	'Nigaros Liemenė',		1960,	14.13,	'Chest','Light',	NULL,	8);
INSERT INTO Armor VALUES (21,	'Nigaros Antslauniai',	1340,	15.86,	'Legs',	'Light',	NULL,	8);

INSERT INTO Armor VALUES (22,	'Obelisko Salmas',		2570,	15.86,	'Head',	'Heavy',	NULL,	9);
INSERT INTO Armor VALUES (23,	'Obelisko Kirasa',		4760,	9.35,	'Chest','Heavy',	NULL,	9);
INSERT INTO Armor VALUES (24,	'Obelisko Antslauniai',	3620,	12.86,	'Legs',	'Heavy',	NULL,	9);

INSERT INTO Armor VALUES (25,	'Velnio Salmas',		975,	26.86,	'Head',	'Light',	NULL,	13);
INSERT INTO Armor VALUES (26,	'Velnio Kirasa',		1860,	23.34,	'Chest','Light',	NULL,	13);
INSERT INTO Armor VALUES (27,	'Velnio Antslauniai',	1360,	24.78,	'Legs',	'Light',	NULL,	13);

INSERT INTO Armor VALUES (28,	'Lokio Kepure',			370,	13.36,	'Head',	'Robe',		NULL,	14);
INSERT INTO Armor VALUES (29,	'Liuto Apsiaustas',		680,	18.12,	'Chest','Robe',		NULL,	14);
INSERT INTO Armor VALUES (30,	'Delfino Antslauniai',	790,	15.86,	'Legs',	'Robe',		NULL,	14);

INSERT INTO Armor VALUES (31,	'Siaubo Salmas',		2965,	16.18,	'Head',	'Heavy',	NULL,	14);
INSERT INTO Armor VALUES (32,	'Siaubo Kirasa',		5011,	12.84,	'Chest','Heavy',	NULL,	14);
INSERT INTO Armor VALUES (33,	'Siaubo Antslauniai',	4036,	14.49,	'Legs',	'Heavy',	NULL,	14);

--Inv
INSERT INTO Armor VALUES (34,	'Burtininko Sijonas',	115,	22.11,	'Legs',	'Robe',		1,	NULL);

INSERT INTO Armor VALUES (35,	'Riterio Salmas',		134,	9.5,	'Head',	'Heavy',	3,	NULL);
INSERT INTO Armor VALUES (36,	'Delfino Antslauniai',	790,	15.86,	'Legs',	'Robe',		3,	NULL);
INSERT INTO Armor VALUES (37,	'Felikso Kirasa',		890,	9.14,	'Chest','Heavy',	3,	NULL);

INSERT INTO Armor VALUES (38,	'Lokio Kepure',			370,	13.36,	'Head',	'Robe',		5,	NULL);
INSERT INTO Armor VALUES (39,	'Felikso Kirasa',		890,	9.14,	'Chest','Heavy',	5,	NULL);
INSERT INTO Armor VALUES (40,	'Dumbuldoro Apsiaustas',980,	17.27,	'Chest','Robe',		5,	NULL);

INSERT INTO Armor VALUES (41,	'Riterio Salmas',		134,	9.5,	'Head',	'Heavy',	6,	NULL);
INSERT INTO Armor VALUES (42,	'Riterio Kirasa',		374,	14.5,	'Chest','Heavy',	6,	NULL);

INSERT INTO Armor VALUES (43,	'Dumbuldoro Kepure',	375,	16.76,	'Head',	'Robe',		7,	NULL);

INSERT INTO Armor VALUES (44,	'Mitrilo Salmas',		560,	24.41,	'Head',	'Heavy',	8,	NULL);
INSERT INTO Armor VALUES (45,	'Mitrilo Kirasa',		1690,	17.36,	'Chest','Light',	8,	NULL);

INSERT INTO Armor VALUES (46,	'Nigaros Liemenė',		1960,	14.13,	'Chest','Light',	9,	NULL);
INSERT INTO Armor VALUES (47,	'Mitrilo Antslauniai',	1003,	20.76,	'Legs',	'Light',	9,	NULL);
INSERT INTO Armor VALUES (48,	'Dumbuldoro Sijonas',	670,	18.45,	'Head','Robe',		9,	NULL);

INSERT INTO Armor VALUES (49,	'Dumbuldoro Sijonas',	670,	18.45,	'Head','Robe',		13,	NULL);
INSERT INTO Armor VALUES (50,	'Nigaros Liemenė',		1960,	14.13,	'Chest','Light',	13,	NULL);

INSERT INTO Armor VALUES (51,	'Riterio Salmas',		134,	9.5,	'Head',	'Heavy',	14,	NULL);
INSERT INTO Armor VALUES (52,	'Riterio Kirasa',		374,	14.5,	'Chest','Heavy',	14,	NULL);

INSERT INTO Armor VALUES (53,	'Velnio Salmas',		975,	26.86,	'Head',	'Light',	15,	NULL);
INSERT INTO Armor VALUES (54,	'Obelisko Salmas',		2570,	15.86,	'Head',	'Heavy',	15,	NULL);
INSERT INTO Armor VALUES (55,	'Obelisko Kirasa',		4760,	9.35,	'Chest','Heavy',	15,	NULL);
INSERT INTO Armor VALUES (56,	'Drakono Antslauniai',	2439,	2.9,	'Legs',	'Heavy',	15,	NULL);
INSERT INTO Armor VALUES (57,	'Dumbuldoro Kepure',	375,	16.76,	'Head',	'Robe',		15,	NULL);
INSERT INTO Armor VALUES (58,	'Dumbuldoro Apsiaustas',980,	17.27,	'Chest','Robe',		15,	NULL);