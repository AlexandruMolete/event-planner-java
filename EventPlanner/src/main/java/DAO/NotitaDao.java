package DAO;

import Model.Notita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotitaDao {
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement findByEventStatement;
    private PreparedStatement deleteStatement;

    public NotitaDao(Connection connection){
        this.connection=connection;
        try {
            createStatement=connection.prepareStatement("INSERT INTO notita VALUES(null,?,?)");
            updateStatement=connection.prepareStatement("UPDATE notita SET Continut=? WHERE ID=?");
            findByEventStatement=connection.prepareStatement("SELECT * FROM notita WHERE EvenimentID=?");
            deleteStatement=connection.prepareStatement("DELETE FROM notita WHERE ID=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Notita notita){
        try {
            createStatement.setString(1,notita.getContinut());
            createStatement.setInt(2,notita.getEvenimentId());
            return createStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Notita notita){
        try {
            updateStatement.setString(1,notita.getContinut());
            updateStatement.setInt(2,notita.getId());
            return updateStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Notita> findByEvent(int evenimentId){
        List<Notita> notite=new ArrayList<>();
        try {
            findByEventStatement.setInt(1,evenimentId);
            ResultSet rs=findByEventStatement.executeQuery();
            while(rs.next()){
                notite.add(
                        new Notita.Builder()
                        .setId(rs.getInt("ID"))
                        .setContinut(rs.getString("Continut"))
                        .setEvenimentId(rs.getInt("EvenimentID"))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notite;
    }

    public boolean delete(int id){
        try {
            deleteStatement.setInt(1,id);
            return deleteStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
