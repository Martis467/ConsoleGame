package GameBase.Items;


/**
 * POCO
 */
public class Armor {
    public int id;
    public String name;
    public int protection;
    public float parry;
    public String aPart;
    public String aType;
    public int inventory;
    public int equipment;

    public Armor(int id, String name, int prot, float par, String ap, String at, int inv, int eqp)
    {
        this.id = id;
        this.name = name;
        this.protection = prot;
        this.parry = par;
        this.aPart = ap;
        this.aType = at;
        this.inventory = inv;
        this.equipment = eqp;
    }
}
