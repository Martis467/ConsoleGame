package GameBase.Items;


/**
 * POCO
 */
public class Material {
    public int id;
    public String name;
    public int quantity;
    public int inventory;

    public Material(int id, String name, int quan, int inv)
    {
        this.id = id;
        this.name = name;
        this.quantity = quan;
        this.inventory = inv;
    }
}
