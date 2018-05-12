package GameBase;

import DatabaseController.DatabaseController;
import GameBase.Items.Armor;
import GameBase.Items.Material;
import GameBase.Items.Weapon;
import GameBase.Player.Player;

import java.util.Scanner;
import java.util.Vector;

public class Market {
    private Vector<Offer> offers;
    private DatabaseController db;

    public Market(DatabaseController db)
    {
        offers = new Vector<>();
        this.db = db;
    }

    public void ListOffers(boolean numbered)
    {
        if(offers.isEmpty())
        {
            System.out.println("Currently there are no offers");
            return;
        }
        System.out.println("Name" + "\t\t" + "Cost" + "\t\t" + "Item name" + "\t\t" + "Item Info");

        if (numbered)
        {
            int i = 1;
            for (Offer off :
                    offers) {
                System.out.print(i +".");off.PrintOffer();
                i++;
            }
        }
        else offers.forEach(of -> of.PrintOffer());
    }

    public void AddOffer(Player player)
    {
        UserInterface.AddItemOptions();
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        boolean success = false;

        if (!ValidChoise(choise, 0, 3))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 3))
            return;

        switch (choise)
        {
            case 1: success = AddWeapon(player); break;
            case 2: success = AddArmor(player); break;
            case 3: success = AddMaterial(player); break;
            default: break;
        }

        if (success) System.out.println("Offer created successfully");
        else System.out.println("Offer was not created");
    }

    private boolean AddMaterial(Player player)
    {
        System.out.println("Player currently has these materials");

        int inventoryId = GetPlayerInventoryId(player.getUsername());

        String sql = "SELECT * FROM Material WHERE "
                + "Inventory = " + inventoryId + ";";
        Vector<Material> materials = db.QuerryMaterials(sql);

        //Printing out material List
        int i = 1;
        for (Material mat :
                materials) {
            System.out.println(i + "." + mat.name + "\t\t" + mat.quantity);
            i++;
        }

        System.out.println("Choose which material to Add and set it's price");

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, materials.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, materials.capacity()))
            return false;

        Material mat = materials.elementAt(choise - 1);

        System.out.println("Cost(lowest value 1 per unit, max value 999 per unit): ");
        choise = scanner.nextInt();

        if (!ValidChoise(choise,mat.quantity, mat.quantity * 1000))
            choise = scanner.nextInt();

        if (!ValidChoise(choise,mat.quantity, mat.quantity * 1000))
            return false;

        offers.add(new Offer(
                player.getUsername(),
                choise,
                null,
                null,
                mat
        ));

        return true;
    }

    private boolean AddArmor(Player player)
    {
        System.out.println("Player currently has these armor parts in his inventory");
        int inventoryId = GetPlayerInventoryId(player.getUsername());

        //Get armor vector and print it out
        String sql = "SELECT * FROM Armor WHERE "
                + "Inventory = " + inventoryId + ";";
        Vector<Armor> armors = db.QuerryArmors(sql);

        int i = 1;
        for (Armor ar :
                armors) {
            System.out.println(i + "." + ar.name + "\t\t" + ar.protection + "\t\t" + ar.parry + "\t\t" + ar.aPart + "\t\t" + ar.aType );
            i++;
        }

        System.out.println("Choose which Armor you want to sell and add and it's price");

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, armors.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, armors.capacity()))
            return false;

        Armor ar = armors.elementAt(choise - 1);

        System.out.println("Cost(lowest value 100 max value 500000 per unit): ");
        choise = scanner.nextInt();

        if (!ValidChoise(choise, 100, 500000))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 100, 500000))
            return false;

        offers.add(new Offer(
                player.getUsername(),
                choise,
                null,
                ar,
                null
        ));
            return true;

    }

    private boolean AddWeapon(Player player)
    {
        System.out.println("Player currently has these weapons in his inventory");
        int inventoryId = GetPlayerInventoryId(player.getUsername());

        //Get armor vector and print it out
        String sql = "SELECT * FROM Weapon WHERE "
                + "Inventory = " + inventoryId + ";";
        Vector<Weapon> weapons = db.QuerryWeapons(sql);

        int i = 1;
        for (Weapon wp :
                weapons) {
            System.out.println(i + "." + wp.name + "\t\t" + wp.damage + "\t\t" + wp.accuracy);
            i++;
        }

        System.out.println("Choose which weapon to sell and add and it's price");

        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, weapons.capacity()))
            return false;

        Weapon wp = weapons.elementAt(choise - 1);

        System.out.println("Cost(lowest value 100 max value 600000 per unit): ");
        choise = scanner.nextInt();

        if (!ValidChoise(choise, 100, 600000))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 100, 600000))
            return false;

        offers.add(new Offer(
                player.getUsername(),
                choise,
                wp,
                null,
                null
        ));
        return true;

    }

    public void BuyOffer(Player player)
    {
        if (offers.isEmpty())
        {
            System.out.println("Currently there are no items in the market");
            return;
        }
        UserInterface.SellItemOptions();
        ListOffers(true);
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        boolean success = false;

        if (!ValidChoise(choise, 0, offers.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, offers.capacity()))
            return;

        Offer offer = offers.elementAt(choise -1);

        //Check if player has enough money to buy item;
        if (!player.HasEnoughMoney(db, offer.getCost()))
        {
            System.out.println("Player does not have enough money to buy the item");
            return;
        }

        int buyerInventoryId = GetPlayerInventoryId(player.getUsername());
        int sellerInventoryId = GetPlayerInventoryId(offer.getPlayerUsername());

        if (offer.getArmor() != null) MakeTrade("Armor", offer.getArmor().id, buyerInventoryId, sellerInventoryId, offer.getCost());
        if (offer.getWeapon() != null) MakeTrade("Weapon", offer.getWeapon().id, buyerInventoryId, sellerInventoryId, offer.getCost());
        if (offer.getMaterial() != null) MakeTrade("Material", offer.getMaterial().id, buyerInventoryId, sellerInventoryId, offer.getCost());

    }


    //Helper functions--------------------------------------------------------
    private boolean ValidChoise(int choise, int low, int high)
    {
        if(choise <= low || choise > high)
        {
            System.out.println("Wrong choise, please try again");
            return false;
        }
        return true;
    }

    private int GetPlayerInventoryId(String username)
    {
        String sql = "SELECT Id FROM Inventory WHERE "
                + "Player = " + "'" + username + "'"
                + " LIMIT 1;";
        int inventoryId = db.QuerryFirst(sql,"Id");

        if (inventoryId == -1)
            System.out.println("Something went very very wrong while adding a material, player has no inventory");

        return inventoryId;
    }

    private void MakeTrade(String table, int itemId,  int buyerId, int sellerId, int offerCost)
    {
        int buyerGold = db.QuerryFirst("SELECT Gold FROM Inventory WHERE Id = " + buyerId + " LIMIT 1;", "Gold");
        int sellerGold = db.QuerryFirst("SELECT Gold FROM Inventory WHERE Id = " + sellerId + " LIMIT 1;", "Gold");

        buyerGold -= offerCost;
        sellerGold += offerCost;

        //Make a trade
        String updateSql  = "UPDATE " + table + " SET Inventory = " + buyerId + " WHERE Id = " + itemId + ";";
        String buyerUpdateGoldSql = "UPDATE Inventory SET Gold = " + buyerGold + " WHERE Id = " + buyerId + ";";
        String sellerUpdateGoldSql = "UPDATE Inventory SET Gold = " + sellerGold + " WHERE Id = " + sellerId + ";";

        if (db.Querry(updateSql) && db.Querry(buyerUpdateGoldSql) && db.Querry(sellerUpdateGoldSql))
            System.out.println("The trade was successful");
        else
            System.out.println("Something went wrong and the trade failed");
    }

}
