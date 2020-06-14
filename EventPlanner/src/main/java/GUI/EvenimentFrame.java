package GUI;

import Controller.EvenimentController;
import Model.Cont;
import Model.Eveniment;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class EvenimentFrame extends JFrame{
    private JPanel mainPanel;
    private JList listaEvenimente;
    private JButton ziuaCurentaButton;
    private JComboBox dateComboBox;
    private JLabel dateLabel;
    private JButton adaugaEvenimentButton;
    private JButton stergeEvenimentButton;
    private JButton vedetiNotiteleButton;
    private JLabel utilizatorLabel;
    private JLabel numeUtilizatorLabel;
    private JLabel adaugareEvenimentLabel;
    private JLabel numeEvenimentLabel;
    private JTextField textField1;
    private JLabel dataEvenimentLabel;
    private JTextField textField2;
    private JButton afiseazaButton;
    private JButton afiseazaToateEvenimenteleButton;
    private Cont cont;
    private DefaultListModel<Eveniment> listModel;
    private DefaultComboBoxModel<LocalDateTime> comboBoxModel;

    public EvenimentFrame(Cont cont){
        this.cont=cont;
        listModel=new DefaultListModel<>();
        listaEvenimente.setModel(listModel);
        afisareEvenimente();
        comboBoxModel=new DefaultComboBoxModel<>();
        dateComboBox.setModel(comboBoxModel);
        setDateComboBox();
        adaugaEvenimentButton.addActionListener(ev1->adaugaEveniment());
        afiseazaButton.addActionListener(ev2->afisareEvenimenteDataSelectata());
        afiseazaToateEvenimenteleButton.addActionListener(ev6->afisareEvenimente());
        stergeEvenimentButton.addActionListener(ev3->stergeEveniment());
        vedetiNotiteleButton.addActionListener(ev4->vedetiNotitele());
        ziuaCurentaButton.addActionListener(ev5->afisareEvenimenteZiCurenta());
        numeUtilizatorLabel.setText(this.cont.getNume());
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void afisareEvenimente(){
        List<Eveniment> evenimente= EvenimentController
                .getInstance()
                .findByCont(cont.getId());
        evenimente.sort(Comparator.comparing(Eveniment::getData));
        notificaEveniment(evenimente);
        listModel.clear();
        evenimente.forEach(listModel::addElement);
    }

    private void afisareEvenimente(Timestamp data){
        List<Eveniment> evenimente= EvenimentController
                .getInstance()
                .findByDate(data,cont.getId());
        evenimente.sort(Comparator.comparing(Eveniment::getData));
        listModel.clear();
        evenimente.forEach(listModel::addElement);
    }

    private void afisareEvenimenteDataSelectata(){
        Optional<LocalDateTime> data=Optional.ofNullable((LocalDateTime)dateComboBox.getSelectedItem());
        data.ifPresent(d->afisareEvenimente(Timestamp.valueOf(d)));
    }

    private void afisareEvenimenteZiCurenta(){
        List<Eveniment> evenimente=EvenimentController
                .getInstance()
                .findByCurrentDate();
        evenimente.sort(Comparator.comparing(Eveniment::getData));
        listModel.clear();
        evenimente.forEach(listModel::addElement);
    }

    private void adaugaEveniment(){
        String numeEveniment=textField1.getText();
        LocalDateTime dataEveniment=LocalDateTime.parse(textField2.getText());
        Eveniment eveniment=new Eveniment.Builder()
                .setId(0)
                .setNume(numeEveniment)
                .setData(dataEveniment)
                .setContId(cont.getId())
                .build();
        boolean rez=EvenimentController.getInstance().create(eveniment);
        if(rez){
            afisareEvenimente();
            setDateComboBox();
        }else{
            JOptionPane.showMessageDialog(null, "Eroare");
        }
        textField1.setText("");
        textField2.setText("");
    }

    private void notificaEveniment(List<Eveniment> evenimente){
        for (Eveniment eveniment : evenimente) {
            if(eveniment.getData().toLocalDate().equals(LocalDate.now())&&eveniment.getData().getHour()==LocalDateTime.now().getHour()) {
                if (eveniment.getData().getMinute() - LocalDateTime.now().getMinute() <= 15) {
                    JOptionPane.showMessageDialog(null, "Evenimentul " + eveniment.getNume() + " va incepe in 15 minute.");
                }
            }
        }
    }

    private void setDateComboBox(){
        TreeSet<LocalDateTime> date=new TreeSet<>(EvenimentController
                .getInstance()
                .findAllDates());
        comboBoxModel.removeAllElements();
        date.forEach(comboBoxModel::addElement);
    }

    private void stergeEveniment(){
        Optional<Eveniment> selected= Optional.ofNullable((Eveniment)listaEvenimente.getSelectedValue());
        boolean rez= selected.filter(eveniment -> EvenimentController.getInstance().delete(eveniment.getNume())).isPresent();
        if(rez){
            afisareEvenimente();
            setDateComboBox();
        }else{
            JOptionPane.showMessageDialog(null, "Nu s-a putut sterge evenimentul. Va rugam selectati un eveniment din lista.");
        }
    }

    private void vedetiNotitele(){
        Optional<Eveniment> selected=Optional.ofNullable((Eveniment)listaEvenimente.getSelectedValue());
        selected.ifPresent(NotitaFrame::new);
        if(selected.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nu s-au putut afisa notitele evenimentului. Va rugam selectati un eveniment din lista.");
        }
    }
}
