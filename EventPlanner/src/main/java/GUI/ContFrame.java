package GUI;

import Controller.ContController;
import Model.Cont;

import javax.swing.*;
import java.util.Arrays;
import java.util.Optional;

public class ContFrame extends JFrame{
    private JTextField textField1;
    private JLabel numeLabel;
    private JPanel mainPanel;
    private JLabel parolaLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton creareButton;
    private JButton stergereButton;

    public ContFrame(){
        creareButton.addActionListener(ev1->creareCont());
        stergereButton.addActionListener(ev2->stergereCont());
        logInButton.addActionListener(ev3->logIn());
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void creareCont(){
        String nume=textField1.getText();
        String parola= Arrays.toString(passwordField1.getPassword());
        Cont cont=new Cont.Builder()
                .setId(0)
                .setNume(nume)
                .setParola(parola)
                .build();
        boolean rez= ContController.getInstance().create(cont);
        if (rez) {
            JOptionPane.showMessageDialog(null,"Contul a fost creat cu succes.");
        }else{
            JOptionPane.showMessageDialog(null,"Nume utilizator sau parola exista deja.");
        }
        textField1.setText("");
        passwordField1.setText("");
    }

    private void stergereCont(){
        String nume=textField1.getText();
        String parola=Arrays.toString(passwordField1.getPassword());
        boolean rez=ContController.getInstance().delete(nume,parola);
        if (rez) {
            JOptionPane.showMessageDialog(null,"Contul a fost sters cu succes.");
        }else{
            JOptionPane.showMessageDialog(null,"Nu s-a putut efectua stergerea. Nume utilizator sau parola gresite.");
        }
        textField1.setText("");
        passwordField1.setText("");
    }

    private void logIn(){
        String nume=textField1.getText();
        String parola=Arrays.toString(passwordField1.getPassword());
        Optional<Cont> cont=ContController.getInstance().findCont(nume,parola);
        if(cont.isPresent()){
            new EvenimentFrame(cont.get());
        }else{
            JOptionPane.showMessageDialog(null,"Nume utilizator sau parola gresite.");
        }
        textField1.setText("");
        passwordField1.setText("");
    }
}
