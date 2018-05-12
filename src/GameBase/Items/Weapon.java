package GameBase.Items;

/**
 * POCO
 */
public class Weapon {
    public int id;
    public String name;
    public int damage;
    public float accuracy;
    public int inventory;
    public int equipment;

    public Weapon(int id, String name, int dmg, float acc, int inv, int eqp)
    {
        this.id = id;
        this.name = name;
        this.damage = dmg;
        this.accuracy = acc;
        this.inventory = inv;
        this.equipment = eqp;
    }

}
