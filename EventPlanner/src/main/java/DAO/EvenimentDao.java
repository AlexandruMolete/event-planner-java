package DAO;

import Model.Eveniment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvenimentDao {
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findAllEventsStatement;
    private PreparedStatement findAllDatesStatement;
    private PreparedStatement findByDateStatement;
    private PreparedStatement findByContStatement;
    private PreparedStatement deleteStatement;

    public EvenimentDao(Connection connection){
        this.connection=connection;
        try {
            createStatement=connection.prepareStatement("INSERT INTO eveniment VALUES(null,?,?,?)");
            findAllEventsStatement=connection.prepareStatement("SELECT * FROM eveniment");
            findAllDatesStatement=connection.prepareStatement("SELECT Data FROM eveniment");
            findByDateStatement=connection.prepareStatement("SELECT * FROM eveniment WHERE Data=? AND ContID=?");
            findByContStatement=connection.prepareStatement("SELECT * FROM eveniment WHERE ContID=?");
            deleteStatement=connection.prepareStatement("DELETE FROM eveniment WHERE Nume=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Eveniment eveniment){
        try {
            createStatement.setString(1,eveniment.getNume());
            createStatement.setTimestamp(2, Timestamp.valueOf(eveniment.getData()));
            createStatement.setInt(3,eveniment.getContId());
            return createStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<LocalDateTime> findAllDates(){
        List<LocalDateTime> dates=new ArrayList<>();
        try {
            ResultSet rs=findAllDatesStatement.executeQuery();
            while(rs.next()){
                dates.add(rs.getTimestamp("Data").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public List<Eveniment> findAllEvents(){
        List<Eveniment>evenimente=new ArrayList<>();
        try{
            ResultSet rs=findAllEventsStatement.executeQuery();
            while (rs.next()){
                evenimente.add(new Eveniment.Builder()
                        .setId(rs.getInt("ID"))
                        .setNume(rs.getString("Nume"))
                        .setData(rs.getTimestamp("Data").toLocalDateTime())
                        .setContId(rs.getInt("ContID"))
                        .build()
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return evenimente;
    }

    public List<Eveniment> findByDate(Timestamp date,int accountId){
        List<Eveniment> evenimente=new ArrayList<>();
        try {
            findByDateStatement.setTimestamp(1,date);
            findByDateStatement.setInt(2,accountId);
            ResultSet rs=findByDateStatement.executeQuery();
            while(rs.next()){
                evenimente.add(new Eveniment.Builder()
                                .setId(rs.getInt("ID"))
                                .setNume(rs.getString("Nume"))
                                .setData(rs.getTimestamp("Data").toLocalDateTime())
                                .setContId(rs.getInt("ContID"))
                                .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenimente;
    }

    public List<Eveniment> findByCont(int accountId){
        List<Eveniment> evenimente=new ArrayList<>();
        try {
            findByContStatement.setInt(1,accountId);
            ResultSet rs=findByContStatement.executeQuery();
            while(rs.next()){
                evenimente.add(new Eveniment.Builder()
                        .setId(rs.getInt("ID"))
                        .setNume(rs.getString("Nume"))
                        .setData(rs.getTimestamp("Data").toLocalDateTime())
                        .setContId(rs.getInt("ContID"))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenimente;
    }

    public boolean delete(String nume){
        try {
            deleteStatement.setString(1,nume);
            return deleteStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
