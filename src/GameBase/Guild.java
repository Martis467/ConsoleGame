package GameBase;

import GameBase.Enumerations.GuildAssociation;

public class Guild {
    private String name;
    private String association;
    private int level;
    private int rank;

    public Guild(String name, String guildAssociation, int level, int rank )
    {
        this.name = name;
        this.association = guildAssociation;
        this.level = level;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
