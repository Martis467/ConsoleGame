package GameBase.Player;

import DatabaseController.DatabaseController;
import GameBase.Items.Armor;
import GameBase.Items.Material;
import GameBase.Items.Weapon;
import GameBase.UserInterface;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    DatabaseController db;
    private int id;
    private int gold;
    private String player;

    public Inventory(int id, int gold, String player)
    {

        this.id = id;
        this.gold = gold;
        this.player = player;

    }

    public void DropItem(Player player)
    {
        System.out.println("Which item you would like to drop?");

        Vector<Weapon> weapons = db.QuerryWeapons("SELECT * FROM Weapon WHERE Inventory = " + id +";");
        Vector<Armor> armors = db.QuerryArmors("SELECT * FROM Armor WHERE Inventory = " + id +";");
        Vector<Material> materials = db.QuerryMaterials("SELECT * FROM Material WHERE Inventory = " + id +";");

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
        materials.forEach(mat -> {
            System.out.println(i.get() + "." + mat.name + "\t\t" + mat.quantity);
        });

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity() + armors.capacity() + materials.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity() + armors.capacity() + materials.capacity()))
            return;

        //Creating Sql statement
        String table;
        int id;
        int stack = weapons.capacity(); //A temp var for measuring which item to delete;

        if(choise > stack) stack = armors.capacity();
        else
        {
            table = "Weapon";
            id = weapons.elementAt(choise - 1).id;
        }

        if (choise > stack) stack += materials.capacity();
        else
        {
            table = "Armor";
            id = armors.elementAt(choise - stack - 1).id;
        }

        if (choise > stack)
        {
            System.out.println("Something went wrong in Dropping item");
            return;
        }
        else
        {
            table = "Material";
            id = materials.elementAt(choise - stack - 1).id;
        }


        String sql = "DELETE FROM " + table + " WHERE Id =" + id + ";";

        if (db.Querry(sql)) System.out.println("Item has been dropped");
        else System.out.println("Something went wrong while dropping an item");

    }
    public void EnhanceItem(Player player)
    {
        UserInterface.EnhanceItemInfo();

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 2))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 2))
            return;

        Vector<Armor> armors = db.QuerryArmors("SELECT * FROM Armor WHERE Inventory = " + id +";");

        switch (choise)
        {
            case 1: EnhanceWeapon(player);
            case 2: EnhanceArmor(player);
        }

    }

    private void EnhanceWeapon(Player player)
    {
        if(!player.HasEnoughMoney(db, 15000))
        {
            System.out.println("Player does not have enough money");
            return;
        }
        Vector<Weapon> weapons = db.QuerryWeapons("SELECT * FROM Weapon WHERE Inventory = " + id +";");

        System.out.println("Choose which weapon you would like to enhance:");

        AtomicInteger i = new AtomicInteger(1);

        weapons.forEach(wp -> {
            System.out.println(i.get() + "." + wp.name + "\t\t" + wp.damage + "\t\t" + wp.accuracy);
            i.getAndIncrement();
        });

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if(!ValidChoise(choise,0,weapons.capacity()))
            choise = scanner.nextInt();

        if(!ValidChoise(choise,0,weapons.capacity()))
            return;

        //Enhance chosen weapon
        Weapon wp = weapons.elementAt(choise-1);

        if(Enhancement(wp.name, (float)wp.damage, wp.id, true))
            System.out.println("Weapon enhanced successfully");
        else System.out.println("Weapon enhancement failed");

    }

    private void EnhanceArmor(Player player)
    {
        if(!player.HasEnoughMoney(db, 15000))
        {
            System.out.println("Player does not have enough money");
            return;
        }
        Vector<Armor> armors = db.QuerryArmors("SELECT * FROM Armor WHERE Inventory = " + id +";");

        System.out.println("Choose which weapon you would like to enhance:");

        AtomicInteger i = new AtomicInteger(1);

        armors.forEach(ar -> {
            System.out.println(i.get() + "." + ar.name + "\t\t" + ar.protection + "\t\t" + ar.parry);
            i.getAndIncrement();
        });

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if(!ValidChoise(choise,0,armors.capacity()))
            choise = scanner.nextInt();

        if(!ValidChoise(choise,0,armors.capacity()))
            return;

        //Enhance chosen weapon
        Armor ar = armors.elementAt(choise-1);

        if(Enhancement(ar.name, (float)ar.protection, ar.id, false))
            System.out.println("Armor enhanced successfully");
        else System.out.println("Armor enhancement failed");

    }

    private boolean Enhancement(String name, float stat, int id, boolean weapon)
    {
        //Set enhancment koeficient
        float koef;
        int cost;
        if(weapon)
        {
            koef = 1.3f;
            cost = 15000;
        }
        else
        {
            koef = 1.1f;
            cost = 10000;
        }

        //Adjust player gold
        gold -= cost;
        String sql = "UPDATE Player SET Gold = " + gold + "WHERE Id =" + id +";";
        if (!db.Querry(sql)) System.out.println("Something went wrong when updating players gold");


        //Random nr bettween 1 and 100 if less than 50 = failed else succeeded
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100);

        //For testing purpose
        randomNum = 80;
        if (randomNum < 50) return false;

        if (name.length() + 2 > 30)
            name += " +1";
        stat *= koef;
        if (weapon)
        {
            String weaponSql = "UPDATE Weapon SET Name =" + "'" + name + "'," + " Damage =" + (int)Math.ceil(stat) + " WHERE Id =" + id + ";" ;
            if (!db.Querry(weaponSql)) System.out.println("Something went wrong when updating players weapon");
            return true;
        }

        String armorSql = "UPDATE Armor SET Name =" + "'" + name + "'," + " Protection =" + (int)Math.ceil(stat) + " WHERE Id =" + id + ";" ;
        if (!db.Querry(armorSql)) System.out.println("Something went wrong when updating players armor");
        return true;

    }




    //Setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void SetDB(DatabaseController db) { this.db = db;}

    private boolean ValidChoise(int choise, int low, int high)
    {
        if(choise <= low || choise > high)
        {
            System.out.println("Wrong choise, please try again");
            return false;
        }
        return true;
    }
}
