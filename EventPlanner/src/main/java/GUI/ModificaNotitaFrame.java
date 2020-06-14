package GUI;

import Controller.NotitaController;
import Model.Notita;

import javax.swing.*;

public class ModificaNotitaFrame extends JFrame{
    private JTextArea textArea1;
    private JButton modificaButton;
    private JLabel notitaContinutLabel;
    private JPanel mainPanel;
    private Notita notita;

    public ModificaNotitaFrame(Notita notita){
        this.notita=notita;
        modificaButton.addActionListener(ev->modificaNotita());
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void modificaNotita(){
        String continut=textArea1.getText();
        Notita n=new Notita.Builder()
                .setId(notita.getId())
                .setContinut(continut)
                .setEvenimentId(notita.getEvenimentId())
                .build();
        boolean rez= NotitaController.getInstance().update(n);
        if (rez){
            JOptionPane.showMessageDialog(null,"Notita s-a modificat cu succes.");
        }else{
            JOptionPane.showMessageDialog(null,"Nu s-a putut modifica notita.");
        }
        textArea1.setText("");
    }
}
