CREATE INDEX		IX_Inventory_Gold	 		ON Inventory(Gold);
CREATE INDEX		IX_Player_Power_Level		ON Player(Power, Level);
CREATE UNIQUE INDEX UX_Material_Name_Inventory	ON Material(Name, Inventory);
CREATE UNIQUE INDEX UX_Equipment_Player			ON Equipment(Player);
CREATE UNIQUE INDEX UX_Inventory_Player			ON Inventory(Player);
