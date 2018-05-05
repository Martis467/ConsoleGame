package GameBase.Enumerations;

public enum GuildAssociation {
    Merchant(1),
    Thief(2),
    Hunter(3);
    private int val;
    GuildAssociation(int i) {
        val = i;
    }

    public int AsInt() {return val;}
}
