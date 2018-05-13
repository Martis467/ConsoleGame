package GameBase;


/**
 * Only holds text and options panel of UI, no logic or variables
 */
public final class UserInterface {

    public static void Intro()
    {
        System.out.println("WELCOME TO THE GAMEROOM");
        System.out.println("You can do the following actions");
        System.out.println("Register a new player or a Guild");
        //System.out.println("Checkout out a player, see his equipment, inventory");
        System.out.println("Re equip items, drop them");
        System.out.println("Sell/Buy items at the market");
        System.out.println("Try to enhance your equipment");
        //System.out.println("Make players fight(spar or deathmatch)");
        System.out.println();
    }

    public static void Meniu()
    {
        System.out.println("Choose an Action");
        System.out.println("1.List All players");
        System.out.println("2.Register a new player");
        System.out.println("3.Create a new Guild");
        System.out.println("4.Checkout the Market");
        System.out.println("5.Enhance/Drop/Equip an item");
        //System.out.println("6.Player fight");
        System.out.println("6. Exit");
        System.out.print("Enter your action: ");

    }
    public static void PlayerRegistration()
    {
        System.out.println();
        System.out.println("To register a new player you must enter these fields:");
        System.out.println("Username: Only letters and number allowed and can be up to 25 characters");
        System.out.println("Starting level is 1 with a 120 power and 1000 gold");
        System.out.println("You can't create a new guild unless you are above level 30, you can only join one");
        System.out.println("Enter Username: ");

    }

    public static void GuildCreationIntro()
    {
        System.out.println("Guild Creation Fee is 10k gold");
        System.out.println("Choose a player that will create the guild:");
    }

    public static void GuildCreationName()
    {
        System.out.println();
        System.out.println("To create a new guild you must enter a name and choose an association:");
        System.out.println("Name: Only letters and number allowed and can be up to 25 characters");
    }
    public static void GuildCreationAssociation()
    {
        System.out.println();
        System.out.println("Association:");
        System.out.println("1.Thieves");
        System.out.println("2.Hunters");
        System.out.println("3.Merchants");
        System.out.println("Your choise: ");
    }

    public static void MarketPreInfo()
    {
        System.out.println();
        System.out.println("Choose a player to Checkout the market:");
    }

    public static void MarketOptions()
    {
        System.out.println();
        System.out.println("Market options");
        System.out.println("1.List all items");
        System.out.println("2.Buy an item");
        System.out.println("3.Put an item for sale");
    }

    public static void AddItemOptions()
    {
        System.out.println();
        System.out.println("What item would you like to sell");
        System.out.println("1.Weapon");
        System.out.println("2.Armor");
        System.out.println("3.Material");
        System.out.println("Your choise: ");
    }

   public static void SellItemOptions()
   {
       System.out.println();
       System.out.println("Which item you would like to buy?");
       System.out.println("Item list:");
   }

   public static void ActWithPlayerInfo()
   {
       System.out.println();
       System.out.println("1.Drop Item");
       System.out.println("2.Try to enhance an item");
       System.out.println("3.Equip an item");
       System.out.println("4.Take off an item");
       System.out.println("Your choise");
   }

   public static void EnhanceItemInfo()
   {
       System.out.println();
       System.out.println("Enhancing a weapon will cost 15k an armor part 10k and it has 50% success rate");
       System.out.println("Choose which item you would like to enhance:");
       System.out.println("1.Weapon");
       System.out.println("2.Armor");
       System.out.println("Your choise");
   }

   public static void EquipItemInfo()
   {
       System.out.println();
       System.out.println("Select which item you would like to equip:");
   }

   public static void TakeItemOffInfo()
   {
       System.out.println();
       System.out.println("Select which item you would like to take off");
   }
}
