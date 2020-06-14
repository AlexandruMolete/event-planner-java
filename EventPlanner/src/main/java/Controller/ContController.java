package Controller;

import DAO.ContDao;
import Model.Cont;

import java.util.Optional;

public class ContController {
    private static final class SingletonHolder{
        public static final ContController INSTANCE=new ContController();
    }
    private ContDao contDao;
    private ContController(){
        contDao=new ContDao(ConnectionManager.getInstance().getConnection());
    }
    public static ContController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public Optional<Cont> findCont(String nume,String parola){
        return contDao.findByUsername(nume,parola);
    }
    public boolean create(Cont cont){
        Optional<Cont> contOptional=contDao.findByUsername(cont.getNume(),cont.getParola());
        if(contOptional.isEmpty()){
            return contDao.create(cont);
        }else{
            return false;
        }
    }
    public boolean delete(String nume, String parola){
        return contDao.delete(nume,parola);
    }
}
