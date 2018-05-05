package DatabaseController;

import GameBase.Guild;
import GameBase.Player;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class DatabaseController {

    private static Connection con;
    private static boolean hasData = false;


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

    public Vector<String> QuerryString(String sql, String columnName)
    {
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

    public boolean QuerryAdd(String sql)
    {
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
