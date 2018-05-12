package GameBase;

import GameBase.Items.*;


public class Offer {

    private String playerUsername;
    private int cost;
    private Weapon weapon;
    private Armor armor;
    private Material material;

    public Offer(String playerUsername, int cost, Weapon weapon, Armor armor, Material material)
    {
        this.playerUsername = playerUsername;
        this.cost = cost;
        this.weapon = weapon;
        this.armor = armor;
        this.material = material;
    }

    public void PrintOffer()
    {
        System.out.print(playerUsername + "\t\t" + cost + "\t\t");
        if(weapon != null) System.out.println(weapon.name + "\t\t" + weapon.damage + "\t\t" + weapon.accuracy);
        if(armor != null) System.out.println(armor.name + "\t\t" + armor.protection + "\t\t" + armor.parry + "\t\t" + armor.aPart + " " + armor.aType);
        if(material != null) System.out.println(material.name + "\t\t" + material.quantity);
    }

    //Getters

    public String getPlayerUsername() {
        return playerUsername;
    }

    public int getCost() {
        return cost;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Material getMaterial() {
        return material;
    }
}
