package DatabaseController;

import GameBase.Guild;
import GameBase.Items.Armor;
import GameBase.Items.Material;
import GameBase.Items.Weapon;
import GameBase.Player.Equipment;
import GameBase.Player.Inventory;
import GameBase.Player.Player;

import java.sql.*;
import java.util.Vector;

public class DatabaseController {

    private static Connection con;

    public DatabaseController()
    {
        if(con == null) GetConnection();
    }

    private void GetConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:GameBase.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Exception trying to open database");
        }

    }
    private void CloseConnection()
    {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Caught exception trying to close database");
            e.printStackTrace();

        }
    }

    public void LoadPlayers(Vector<Player> players)
    {
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM GuildLayout;");

            //Parse out results
            while (rs.next())
            {
                Guild g = new Guild(
                        rs.getString("Name"),
                        rs.getString("Association"),
                        rs.getInt("Level"),
                        rs.getInt("Rank")
                );
                players.add( new Player(
                        rs.getString("GuildMember"),
                        rs.getShort("PlayerLevel"),
                        rs.getInt("PlayerPower"),
                        g
                ));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param player Username
     * @return players inventory Id, Gold, and players username
     */
    public Inventory LoadInventory(String player)
    {
        Inventory inventory = null;
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Inventory WHERE Player = " + "'" + player + "' LIMIT 1;");

            //Parse out results
            while (rs.next())
            {
                inventory = new Inventory(rs.getInt("Id"),
                        rs.getInt("Gold"),
                        rs.getString("Player"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return inventory;
        }
        return inventory;
    }

    /**
     *
     * @param player Username
     * @return players equipment id, Damage, Protection, Accuracy, Parry, and players username
     */
    public Equipment LoadEquipment(String player)
    {
        Equipment equipment = null;
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Equipment WHERE Player = " + "'" + player + "' LIMIT 1;");

            //Parse out results
            while (rs.next())
            {
                equipment = new Equipment(
                        rs.getInt("Id"),
                        rs.getInt("TotalDamage"),
                        rs.getInt("TotalProtection"),
                        rs.getFloat("Accuracy"),
                        rs.getFloat("ParryRate"),
                        rs.getString("Player")
                );
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return equipment;
        }
        return equipment;

    }

    public Armor LoadArmor(String sql)
    {
        Armor armor = null;
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            //Parse out results
            while (rs.next())
            {
                armor = new Armor(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getInt("Protection"),
                        rs.getFloat("ParryRate"),
                        rs.getString("ArmorPart"),
                        rs.getString("ArmorType"),
                        rs.getInt("Inventory"),
                        rs.getInt("Equipment")
                );
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return armor;
        }
        return armor;

    }
    public Weapon LoadWeapon(String sql)
    {
        Weapon weapon = null;
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            //Parse out results
            while (rs.next())
            {
                weapon = new Weapon(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getInt("Damage"),
                        rs.getFloat("Accuracy"),
                        rs.getInt("Inventory"),
                        rs.getInt("Equipment")
                );
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return weapon;
        }
        return weapon;

    }
    /**
     *
     * @param sql statement to be executed
     * @param columnName from which the strings are parsed
     * @return  a vector of strings
     */
    public Vector<String> QuerryVector(String sql, String columnName)
    {
        if (con == null) GetConnection();
        Vector<String> guilds = new Vector<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                guilds.add(rs.getString(columnName));
            }

            rs.close();
            statement.close();
            return guilds;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guilds;
    }

    public Vector<Material> QuerryMaterials(String sql)
    {
        if (con == null) GetConnection();
        Vector<Material> materials = new Vector<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
               materials.add( new Material(
                       rs.getInt("Id"),
                       rs.getString("Name"),
                       (int)rs.getShort("Quantity"),
                       rs.getInt("Inventory")
               ));
            }

            rs.close();
            statement.close();
            return materials;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    public Vector<Armor> QuerryArmors(String sql)
    {
        if (con == null) GetConnection();
        Vector<Armor> armors = new Vector<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                armors.add(new Armor(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getInt("Protection"),
                        rs.getFloat("ParryRate"),
                        rs.getString("ArmorPart"),
                        rs.getString("ArmorType"),
                        rs.getInt("Inventory"),
                        rs.getInt("Equipment")
                ));
            }

            rs.close();
            statement.close();
            return armors;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return armors;
    }

    public Vector<Weapon> QuerryWeapons(String sql)
    {
        if (con == null) GetConnection();
        Vector<Weapon> weapons = new Vector<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                weapons.add( new Weapon(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getInt("Damage"),
                        rs.getFloat("Accuracy"),
                        rs.getInt("Inventory"),
                        rs.getInt("Equipment")
                ));
            }

            rs.close();
            statement.close();
            return weapons;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weapons;
    }

    /**
     *
     * @param sql
     * @param columnName
     * @return
     */
    public int QuerryFirst(String sql, String columnName)
    {
        if (con == null) GetConnection();
        int value = -1;
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                value = rs.getInt(columnName);
            }

            rs.close();
            statement.close();
            return value;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     * @param sql - Add/Update/Delete Statement
     * @return true - on success false - if error occurred
     */
    public boolean Querry(String sql)
    {
        if (con == null) GetConnection();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

}
