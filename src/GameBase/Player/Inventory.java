package GameBase.Player;

import DatabaseController.DatabaseController;
import GameBase.Items.Armor;
import GameBase.Items.Material;
import GameBase.Items.Weapon;

import java.util.Scanner;
import java.util.Vector;
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
        int stack = weapons.capacity(); //A temp var for measuring whic item to delete;

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
    public void EnchanceItem(Player player)
    {

    }

    public void EquipItem(Player player)
    {

    }

    public void TakeOffItem(Player player)
    {

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
