package GameBase;

import DatabaseController.DatabaseController;
import GameBase.Player.Inventory;
import GameBase.Player.Player;


import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameRoom {

    private Vector<Player> playerList;
    private Market market;
    private DatabaseController db;  //Reference to Database
    private Pattern pattern;    //Used for string validation


    public GameRoom ()
    {
        playerList = new Vector<Player>();
        db = new DatabaseController();
        market = new Market(db);
        pattern = Pattern.compile("[a-zA-Z0-9]*"); //Regex for allowing only letters and numbers

        db.LoadPlayers(playerList);

        GameRoomLoop();
    }

    private void GameRoomLoop()
    {
        UserInterface.Intro();
        System.out.println("Online players: " + playerList.size());
        int option = 0;
        Scanner input = new Scanner(System.in);

        while (option != -1)
        {
            //Printing out the choises a player can make
            UserInterface.Meniu();

            option = input.nextInt();
            switch (option)
            {
                case 1:
                    //List all players
                    ListAllPlayer(false);
                    break;

                case 2:
                    //Register player
                    RegisterNewPlayer();
                    break;

                case 3:
                    //Create guild
                    CreateNewGuild();
                    break;

                case 4:
                    //Checkout the Market
                    EnterMarket();
                    break;

                case 5:
                    //Enhance/Drop/Equip an item
                    ActWithPlayer();
                    break;

                case 6:
                    //Player fight
                    break;

                case 7:
                    //Exit
                    option = -1;
                    break;

                default:
                    option = 0;
                    System.out.println("Wrong action, try again:");
                    break;

            }

            if (option == 0) continue;
        }
        System.out.println("Thank you for playing");
        return;

    }

    private void ListAllPlayer(boolean numbered)
    {
        playerList.clear();
        db.LoadPlayers(playerList);
        System.out.println("Username\t\tLevel\t\tPower\t\tGuild\t\t");
        if (numbered)
        {
            int i = 1;
            for (Player pl :
                    playerList) {
                System.out.print(i + ".");
                pl.PlayerLayout();
                i++;
            }
        }
        else    playerList.forEach(pl -> pl.PlayerLayout());
    }

    private void RegisterNewPlayer()
    {
        Scanner scanner = new Scanner(System.in);
        UserInterface.PlayerRegistration();
        String username = PickName(scanner);

        //Player username List for validation
        Vector<String> playerNames = new Vector<>();
        playerList.forEach(pl -> playerNames.add(pl.getUsername()));

        if (!ValidateName(scanner, playerNames, username))
        {
            System.out.println("Registration failed");
            return;
        }
        System.out.println(); //New line for easier readabillity

        System.out.println("Choose a Guild");
        String guild = PickGuild(scanner);

        String playerSql = "INSERT INTO Player(Username,Level,Power,Guild) VALUES("
                + "'" + username + "',"
                + 3         + ","
                + 120       + ","
                + "'" + guild + "'" + ");";

        String inventorySql = "INSERT INTO Inventory(Gold, Player) "
                + "VALUES("
                + 1000      + ","
                + "'" + username  + "');";

        String equipmentSql = "INSERT INTO Equipment(TotalDamage,Accuracy,TotalProtection,ParryRate,Player) "
                + "VALUES("
                + 50        + ","
                + 40        + ","
                + 50        + ","
                + 40        + ","
                + "'" + username  + "');";

        if( db.Querry(playerSql) && db.Querry(inventorySql) && db.Querry(equipmentSql))
        {
            System.out.println("New player has been created successfully");
        }
        else
            System.out.println("Something went wrong while registrating new player");

        System.out.println();
    }

    private void CreateNewGuild()
    {
        UserInterface.GuildCreationIntro();
        ListAllPlayer(true);
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, playerList.capacity()))
            choise = scanner.nextInt();
        if (!ValidChoise(choise, 0, playerList.capacity()))
            return;

        Player pl;

        if(!playerList.elementAt(choise-1).HasEnoughMoney(db,10000))
        {
            System.out.println("The player Does not have enough gold");
            return;
        }
        //Reference to player, needed for sqlUpdate
        pl = playerList.elementAt(choise-1);

        //Steps how to create new guild
        UserInterface.GuildCreationName();
        String guildName = PickName(scanner);

        //Guild List for validation
        String sql = "SELECT DISTINCT Name FROM Guild;";
        Vector<String> guilds = db.QuerryVector(sql, "Name");


        if (!ValidateName(scanner,guilds,guildName))
        {
            System.out.println("Guild creation failed");
            return;
        }
        //Further steps to create a guild
        UserInterface.GuildCreationAssociation();
        String association="";

        choise = scanner.nextInt();
        if(!ValidChoise(choise, 0, 3))
            choise = scanner.nextInt();

        switch (choise)
        {
            case 1: association = "Thief"; break;
            case 2: association = "Hunter"; break;
            case 3: association = "Merchant"; break;
            default: break;
        }

        if (!association.contains("Thief") && !association.contains("Hunter") && !association.contains("Merchant"))
        {
            System.out.println("Guild creation failed Association was not in (Thief, Hunter, Merchant");
            return;
        }
        //Get lowest rank(highest number)
        int lowestRank = db.QuerryFirst("SELECT MAX(Rank) AS r FROM Guild LIMIT 1;", "r");
            lowestRank++;
        //create an SQL statement
        String sqlAdd = "INSERT INTO Guild(Name,Association,Level,Rank) VALUES("
                + "'" + guildName + "',"
                + "'" + association + "',"
                + 1 + ","
                + lowestRank + ");";
        String sqlUpdate = "UPDATE Player " +
                "SET Guild = '" + guildName + "'"
                + " WHERE Username = '" + pl.getUsername() + "';";

        if(db.Querry(sqlAdd) && db.Querry(sqlUpdate))
            System.out.println("New guild created successfully");
        else
            System.out.println("Something went wrong while inserting/updating a guild");
        System.out.println();
    }

    private void EnterMarket()
    {
        //Player choise to go in to the market
        UserInterface.MarketPreInfo();

        ListAllPlayer(true);
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, playerList.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, playerList.capacity()))
            return;

        Player pl = playerList.elementAt(choise-1);

        //Market Options
        UserInterface.MarketOptions();
        choise = scanner.nextInt();
        if (!ValidChoise(choise, 0, 3))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, 3))
            return;

        switch (choise)
        {
            case 1: market.ListOffers(false); break;
            case 2: market.BuyOffer(pl); break;
            case 3: market.AddOffer(pl); break;
            default: break;
        }

    }

    private void ActWithPlayer()
    {
        System.out.println("Choose a player:");
        Scanner scanner = new Scanner(System.in);
        ListAllPlayer(true);

        int choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, playerList.capacity()))
            choise = scanner.nextInt();

        if (!ValidChoise(choise, 0, playerList.capacity()))
            return;

        Player pl = playerList.elementAt(choise-1);

        Inventory inventory = db.LoadInventory(pl.getUsername());
        inventory.SetDB(db);

        UserInterface.ActWithPlayerInfo();

        choise = scanner.nextInt();

        switch (choise)
        {
            case 1: inventory.DropItem(pl); break;
            case 2: inventory.EnchanceItem(pl); break;
            case 3: inventory.EquipItem(pl); break;
            case 4: inventory.TakeOffItem(pl); break;
            default: break;
        }

    }

    ///Helper functions-------------------------------------------------------------------

    /**
     *
     * @param scanner var input
     * @return users chosen guild
     */
    private String PickGuild(Scanner scanner)
    {
        String sql = "SELECT DISTINCT Name FROM Guild;";
        Vector<String> guilds = db.QuerryVector(sql, "Name");

        ///Printing out the guild selections
        int i = 1;
        for (String s :
                guilds) {
            System.out.println(i + "." + s);
            i++;
        }

        //Scan int and return string
        System.out.println("Your choise: ");
        int choise = scanner.nextInt();
        if (choise > guilds.capacity() || choise <= 0)
        {
            System.out.println("Wrong choise, try again:");
            choise = scanner.nextInt();
        }
        if (choise > guilds.capacity() || choise <= 0) return "";
        return guilds.get(choise - 1);
    }

    /**
     *
     * @param scanner - input var
     * @return unique name
     */
    private String PickName(Scanner scanner)
    {
        String name="";

        while (name.isEmpty()) name = scanner.nextLine();


        Matcher matcher = pattern.matcher(name);
        //Checking if username has valid format
        if(!matcher.matches())
        {
            System.out.println("Username Can only contain letters and numbers, try again");
            name = scanner.nextLine();
            matcher = pattern.matcher(name);
            if (!matcher.matches()) return "";
        }
        return name;
    }

    //Name Checking if unique
    private boolean ValidateName(Scanner scanner, Vector<?> selection, String name)
    {
        if (name.isEmpty()) return false;

        if(name.length() > 25) return false;
        if(selection.contains(name))
        {
            System.out.println("Username already taken try again");
            System.out.println("Enter username: ");
            name = scanner.nextLine();
        }
        if (selection.contains(name))
            return false;

        return true;
    }
    //Validates if number belongs to an interval
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

}
