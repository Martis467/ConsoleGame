package GameBase.Player;

import DatabaseController.DatabaseController;
import GameBase.Items.Armor;
import GameBase.Items.Weapon;
import GameBase.UserInterface;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Equipment {
    private int id;
    private int totalDamage;
    private int totalProtection;
    private float accuracy;
    private float parryRate;
    private String player;
    private DatabaseController db;


    public Equipment(int id, int totalDamage, int totalProtection, float accuracy, float parryRate, String player) {
        this.id = id;
        this.totalDamage = totalDamage;
        this.totalProtection = totalProtection;
        this.accuracy = accuracy;
        this.parryRate = parryRate;
        this.player = player;
    }

    public void EquipItem()
    {
        UserInterface.EquipItemInfo();

        Vector<Weapon> weapons = db.QuerryWeapons("SELECT * FROM Weapon WHERE Inventory = " + id +";");
        Vector<Armor> armors = db.QuerryArmors("SELECT * FROM Armor WHERE Inventory = " + id +";");

        //Print out all items;
        AtomicInteger i = new AtomicInteger(1);
        weapons.forEach(wp -> {
            System.out.println(i.get() + "." + wp.name + "\t\t" + wp.damage + "\t\t" + wp.accuracy);
            i.getAndIncrement();
        });
        armors.forEach(ar -> {
            System.out.println(i.get() + "." + ar.name + "\t\t" + ar.protection + "\t\t" + ar.parry + "\t\t" + ar.aPart + " " + ar.aType);
            i.getAndIncrement();
        });

        System.out.println("Your choise:");

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity() + armors.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity() + armors.capacity()))
            return;

        if(choise > weapons.capacity())
        {
            //Get equipped armor
            Armor inArmor = armors.elementAt(choise - weapons.capacity() - 1);
            Armor eqArmor = db.LoadArmor("SELECT * FROM Armor WHERE Equipment = " + id + "AND ArmorPart =" + "'" + inArmor.aPart + "';");

            totalProtection = totalProtection - eqArmor.protection + inArmor.protection;
            parryRate = parryRate - eqArmor.parry + inArmor.parry;

            String takeOff = "UPDATE Armor SET Inventory = " + this.id + ", Equipment = NULL" + " WHERE Id ="
                    + eqArmor.id + "AND ArmorPart =" + "'" + inArmor.aPart + "';";
            String equip = "UPDATE Armor SET Equipment = " + this.id + ", Inventory = NULL" + " WHERE Id ="
                    + inArmor.id + "AND ArmorPart =" + "'" + eqArmor.aPart + "';";
            String equipmentUpdate = "UPDATE Equipment SET TotalProtection =" + totalProtection
                    + ", ParryRate =" + parryRate + " WHERE Id=" + this.id + ";";
        }
        else
        {
            //Get equipped weapon
            Weapon eqWeapon = db.LoadWeapon("SELECT * FROM Weapon WHERE Equipment =" + this.id + ";");
            //Get inventory weapon
            Weapon inWeapon = weapons.elementAt(choise - 1);

            totalDamage = totalDamage - eqWeapon.damage + inWeapon.damage;
            accuracy = accuracy -  eqWeapon.accuracy + inWeapon.accuracy;

            String takeOff = "UPDATE Weapon SET Inventory = " + this.id + ", Equipment = NULL WHERE Id =" + eqWeapon.id + ";";
            String equip = "UPDATE Weapon SET Equipment =" + this.id + ", Invetory = NULL WHERE Id =" + inWeapon.id + ";";
            String equipmentUpdate = "UPDATE Equipment SET TotalDamage =" + totalDamage + ", Accuracy =" + accuracy + " WHERE Id=" + this.id + ";";

            if (db.Querry(takeOff) && db.Querry(equip) && db.Querry(equipmentUpdate))
                System.out.println("Item change successfully");
            else System.out.println("Something went wrong while re equipping a weapon");

        }

    }

    public void TakeOffItem()
    {
        UserInterface.TakeItemOffInfo();
        Weapon weapon = db.LoadWeapon("SELECT * FROM Weapon WHERE Equipment =" + this.id + ";");
        Vector<Armor> armors = db.QuerryArmors("SELECT * FROM Armor WHERE Equipment = " + id +";");

        //Print out all items;
        AtomicInteger i = new AtomicInteger(1);
        System.out.println(i.get() + "." + weapon.name + "\t\t" + weapon.damage + "\t\t" + weapon.accuracy);
        i.getAndIncrement();
        armors.forEach(ar -> {
            System.out.println(i.get() + "." + ar.name + "\t\t" + ar.protection + "\t\t" + ar.parry + "\t\t" + ar.aPart + " " + ar.aType);
            i.getAndIncrement();
        });

        System.out.println("Your choise:");

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 1 + armors.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 1 + armors.capacity()))
            return;

        if (choise == 1)
        {
            totalDamage -= weapon.damage;
            accuracy -= weapon.accuracy;

            String takeOff = "UPDATE Weapon SET Inventory = " + this.id+ ", Equipment = NULL WHERE Id =" + weapon.id + ";";
            String equipmentUpdate = "UPDATE Equipment SET TotalDamage =" + totalDamage + ", Accuracy =" + accuracy + " WHERE Id=" + this.id + ";";

            if (db.Querry(takeOff) && db.Querry(equipmentUpdate))
                System.out.println("Weapon has now been moved to inventory");
            else System.out.println("Something went wrong while taking off a weapon");

        }
        else
        {
            Armor armor = armors.elementAt(choise - 2);
            totalProtection -= armor.protection;
            parryRate -= armor.parry;

            String takeOff = "UPDATE Armor SET Inventory = " + this.id+ ", Equipment = NULL WHERE Id =" + weapon.id + ";";
            String equipmentUpdate = "UPDATE Equipment SET TotalDamage =" + totalDamage + ", Accuracy =" + accuracy + " WHERE Id=" + this.id + ";";

            if (db.Querry(takeOff) && db.Querry(equipmentUpdate))
                System.out.println("Armor has now been moved to inventory");
            else System.out.println("Something went wrong while taking off a weapon");
        }

    }


    //Helper functions
    private boolean ValidChoise(int choise, int low, int high)
    {
        if(choise <= low || choise > high)
        {
            System.out.println("Wrong choise, please try again");
            return false;
        }
        return true;
    }


    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getTotalProtecion() {
        return totalProtection;
    }

    public void setTotalProtecion(int totalProtecion) {
        this.totalProtection = totalProtecion;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getParryRate() {
        return parryRate;
    }

    public void setParryRate(float parryRate) {
        this.parryRate = parryRate;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
    public void SetDB(DatabaseController db) {this.db = db;}
}
