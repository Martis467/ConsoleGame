package GameBase;

import DatabaseController.DatabaseController;


import java.util.List;
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
        market = new Market();
        db = new DatabaseController();
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
                    ListAllPlayer();
                    break;

                case 2:
                    //Register player
                    RegisterNewPlayer();
                    break;

                case 3:
                    //Create guild
                    break;

                case 4:
                    //Checkout the Market
                    break;

                case 5:
                    //Enhance a equipment part
                    break;

                case 6:
                    //Choose a character and act with it
                    break;

                case 7:
                    //Player fight
                    break;

                case 8:
                    //Exit
                    option = -1;
                    break;

                default:
                    option = 0;
                    System.out.println("Wrong action, try again:");
                    break;

            }

            if (option == 0) continue;
            UserInterface.Intro();
        }

        System.out.println("Thank you for playing");
        return;

    }

    private void RegisterNewPlayer() {
        UserInterface.PlayerRegistration();
        String username;
        boolean takenFlag = false; //Notes if username is available or not
        Scanner scanner = new Scanner(System.in);

        username = scanner.nextLine();

        Matcher matcher = pattern.matcher(username);
        //Checking if username has valid format
        if(!matcher.matches())
        {
            System.out.println("Username Can only contain letters, try again");
            username = scanner.nextLine();
            matcher = pattern.matcher(username);
            if (!matcher.matches()) return;

        }

        //Checking if username not taken
        for (Player taken :
                playerList) {
            if (taken.getUsername().equals(username))
            {
                System.out.println("Username already taken try again");
                takenFlag = true;
            }
        }
        if (takenFlag)
        {
            username = scanner.nextLine();
        }

        System.out.println(); //New line for easier readabillity

        System.out.println("Choose a Guild");
        String guild = PickGuild(scanner);

        String sql = "INSERT INTO Player(Username,Level,Power,Guild) "
                + "VALUES("
                + "'" + username + "'"  + ","
                + 3         + ","
                + 120       + ","
                + "'" + guild + "'" + ");";

        if( db.QuerryAdd(sql) ) System.out.println("New player has been created successfully");
        else                    System.out.println("Something went wrong");
        System.out.println();
    }

    private void ListAllPlayer() {
        System.out.println("Username\t\tLevel\t\tPower\t\tGuild\t\t");
        for (Player pl :
                playerList) {
            pl.PlayerLayout();
        }
        System.out.println();
    }

    private String PickGuild(Scanner scanner)
    {
        String sql = "SELECT DISTINCT Name FROM Guild;";
        Vector<String> guilds = db.QuerryString(sql, "Name");

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
        return guilds.get(choise - 1);



    }

}
