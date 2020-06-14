package Controller;

import DAO.NotitaDao;
import Model.Notita;

import java.util.List;

public class NotitaController {
    private static final class SingletonHolder{
        public static final NotitaController INSTANCE=new NotitaController();
    }
    private NotitaDao notitaDao;
    private NotitaController(){
        notitaDao=new NotitaDao(ConnectionManager.getInstance().getConnection());
    }
    public static NotitaController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public boolean delete(int id){
        return notitaDao.delete(id);
    }
    public List<Notita> findByEvent(int evenimentId){
        return notitaDao.findByEvent(evenimentId);
    }
    public boolean update(Notita notita){
        return notitaDao.update(notita);
    }
    public boolean create(Notita notita){
        return notitaDao.create(notita);
    }
}
