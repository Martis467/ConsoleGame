package GameBase;


/**
 * Only holds the option panel, no logic or variables
 */
public final class UserInterface {

    public static void Intro()
    {
        System.out.println("WELCOME TO THE GAMEROOM");
        System.out.println("You can do the following actions");
        System.out.println("Register a new player or a Guild");
        System.out.println("Checkout out a player, see his equipment, inventory");
        System.out.println("Reaquip items, change guilds");
        System.out.println("Sell/Buy items at the market");
        System.out.println("Try to enhance your equipment");
        System.out.println("Make players fight(spar or deathmatch)");
        System.out.println();
    }

    public static void Meniu()
    {
        System.out.println("Choose an Action");
        System.out.println("1.List All players");
        System.out.println("2.Register a new player");
        System.out.println("3.Create a new Guild");
        System.out.println("4.Checkout the Market");
        System.out.println("5.Enhance a equipment part");
        System.out.println("6.Choose a character and act with it");
        System.out.println("7.Player fight");
        System.out.println("8. Exit");
        System.out.print("Enter your action: ");

    }
    public static void PlayerRegistration()
    {
        System.out.println();
        System.out.println("To register a new player you must enter these fields:");
        System.out.println("Username: Only letters and number allowed");
        System.out.println("Starting level is 1 with a 120 power");
        System.out.println("Power cannot be more than 350");
        System.out.println("You can't create a new guild unless you are above level 30, but you can join one");
        System.out.print("Enter Username: ");

    }
}
