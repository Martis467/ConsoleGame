CREATE FUNCTION OverDamageCheck() RETURNS "trigger" AS $$
Declare maxDmg INT;
Declare current INT;
	BEGIN
	/*Paimti giklo dmg ir paziuret ar nevirsija playerio*/
	SELECT	Damage INTO current
	FROM	Weapon
	WHERE 	Equipment = NEW.Equipment;

	SELECT	TotalDamage INTO maxDmg
	FROM	Equipment
	WHERE 	Id = NEW.Id;

	IF maxDmg <= current
		THEN RAISE EXCEPTION 'Ginklas negali tureti tokio Damage, kaip players';
		END IF;
		RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER OverDamage
	BEFORE INSERT OR UPDATE ON Equipment
	FOR EACH ROW EXECUTE PROCEDURE OverDamageCheck();

CREATE FUNCTION OverProtectionCheck() RETURNS "trigger" AS $$
Declare maxProt INT;
Declare current	INT;
	BEGIN

	SELECT	Protection INTO current
	FROM	Armor
	WHERE	Equipment = NEW.Equipment;

	SELECT	TotalProtection INTO maxProt
	FROM	Equipment
	WHERE	Id = NEW.Id;

	IF maxProt <= current
		THEN RAISE EXCEPTION 'Sarvai negali tureti tokio Protection, kaip players';
		END IF;
		RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

 CREATE TRIGGER OverProtection
 	BEFORE INSERT OR UPDATE ON Equipment
 	FOR EACH ROW EXECUTE PROCEDURE OverProtectionCheck();