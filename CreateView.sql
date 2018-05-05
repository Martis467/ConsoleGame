/*Materializuota table*/
CREATE VIEW GuildLayout AS
	SELECT  g.Name, g.Association, g.Level, g.Rank,
			p.Username AS GuildMember, p.Power AS PlayerPower, p.Level AS PlayerLevel,
			i.Gold As PlayerGold 
	FROM Guild As g
	INNER JOIN Player AS p ON p.Guild = g.Name
	INNER JOIN Inventory AS i ON i.Player = p.Username
	ORDER BY g.Rank;



CREATE VIEW Highscores AS
	SELECT * FROM Player
	ORDER BY Power DESC, Level ASC; 


CREATE VIEW PlayerLayout AS
	SELECT DISTINCT p.Username, p.Level, p.Power, i.Gold,
			e.TotalDamage AS Damage, e.Accuracy AS Accuracy,
			e.TotalProtection AS Defence, e.ParryRate AS Parry,
			w.Name AS Weapon, w.Damage AS WeaponsDmg
			--h.Helmet AS Helm, c.Chest AS Platebody, l.Legs AS Platelegs
			--(h.Helmet || ' ' || c.Chest || ' ' || l.Legs) AS ArmorSet
	FROM  Player AS p
	INNER JOIN Inventory AS i ON p.Username = i.Player
	INNER JOIN Equipment AS e ON p.Username = e.Player
	LEFT OUTER JOIN Weapon AS w ON e.Id = w.Equipment;


/*WITH Helmets(Equipment, Helmet, ArmorPart) AS
	(SELECT Equipment, Name, ArmorPart FROM Armor
		WHERE ArmorPart = 'Head'),

PlateBodies(Equipment, Chest, ArmorPart) AS
	(SELECT Equipment, Name, ArmorPart FROM Armor
		WHERE ArmorPart = 'Chest'),

PlateLegs(Equipment, Legs, ArmorPart) AS
	(SELECT Equipment, Name, ArmorPart FROM Armor
		WHERE ArmorPart = 'Legs')
--ArmorSet(Equipment, FullSet) AS
	SELECT (Helmet || ' ' || Chest || ' ' || Legs) AS Armor
	--SELECT COUNT(Helmet), COUNT(Chest), COUNT(Legs)
	FROM Helmets AS a
	FULL OUTER JOIN PlateBodies As c ON a.Equipment = c.Equipment
	FULL OUTER JOIN PlateLegs AS l ON a.Equipment = l.Equipment;*/