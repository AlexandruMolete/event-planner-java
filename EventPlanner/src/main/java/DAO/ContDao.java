package DAO;

import Model.Cont;

import java.lang.annotation.Repeatable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ContDao {
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findByUsernameStatement;
    private PreparedStatement deleteStatement;

    public ContDao(Connection connection){
        this.connection=connection;
        try {
            createStatement=connection.prepareStatement("INSERT INTO cont VALUES(null,?,?)");
            findByUsernameStatement=connection.prepareStatement("SELECT * FROM cont WHERE Nume=? AND Parola=?");
            deleteStatement=connection.prepareStatement("DELETE FROM cont WHERE Nume=? AND Parola=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Cont cont){
        try {
            createStatement.setString(1,cont.getNume());
            createStatement.setString(2,cont.getParola());
            return createStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<Cont> findByUsername(String username, String password){
        try {
            findByUsernameStatement.setString(1,username);
            findByUsernameStatement.setString(2,password);
            ResultSet rs=findByUsernameStatement.executeQuery();
            if(rs.next()){
                return Optional.of(
                        new Cont.Builder()
                        .setId(rs.getInt("ID"))
                        .setNume(rs.getString("Nume"))
                        .setParola(rs.getString("Parola"))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean delete(String nume,String parola){
        try {
            deleteStatement.setString(1,nume);
            deleteStatement.setString(2,parola);
            return deleteStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
