package Controller;

import DAO.EvenimentDao;
import Model.Eveniment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvenimentController {
    private static final class SingletonHolder{
        public static final EvenimentController INSTANCE=new EvenimentController();
    }
    private EvenimentDao evenimentDao;
    private EvenimentController(){
        evenimentDao=new EvenimentDao(ConnectionManager.getInstance().getConnection());
    }
    public static EvenimentController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public List<Eveniment> findByDate(Timestamp date,int contId){
        return evenimentDao.findByDate(date,contId);
    }
    public List<Eveniment> findByCont(int contId){
        return evenimentDao.findByCont(contId);
    }
    public List<Eveniment> findByCurrentDate(){
        List<Eveniment>evenimente=evenimentDao.findAllEvents();
        List<Eveniment> listaRezultate=new ArrayList<>();
        LocalDate ziCurenta=LocalDate.now();
        for (Eveniment eveniment : evenimente) {
            if (eveniment.getData().toLocalDate().equals(ziCurenta)) {
                listaRezultate.add(eveniment);
            }
        }
        return listaRezultate;
    }

    public List<LocalDateTime> findAllDates(){
        return evenimentDao.findAllDates();
    }
    public boolean delete(String nume){
        return evenimentDao.delete(nume);
    }
    public boolean create(Eveniment eveniment){
        return evenimentDao.create(eveniment);
    }
}
