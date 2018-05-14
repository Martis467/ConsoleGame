package GameBase.Player;


import DatabaseController.DatabaseController;
import GameBase.Guild;

public class Player {

    private String username;
    private short level;
    private int power;
    private Guild guild;

    public Player(String username, short level, int power, Guild guild)
    {
        this.username = username;
        this.level = level;
        this.power = power;
        this.guild = guild;
    }

    public void PlayerLayout()
    {
        System.out.println(username + "\t\t" + level + "\t\t" + power + "\t\t" + guild.getName());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    //Check if player can purhcase item
    public boolean HasEnoughMoney(DatabaseController db, int fee) {
        String col = "PlayerGold";
        String sql = "SELECT " + col + " "
                    + "FROM GuildLayout "
                    + "WHERE GuildMember = '" + username + "'"
                    + " LIMIT 1;";
        int gold = db.QuerryFirst(sql, col);

        if(gold - fee >= 0) return true;
        else return false;
    }
}
