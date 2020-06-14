package GUI;

import Controller.NotitaController;
import Model.Eveniment;
import Model.Notita;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class NotitaFrame extends JFrame {
    private JPanel mainPanel;
    private JList listaNotite;
    private JLabel evenimentLabel;
    private JButton modificaButton;
    private JButton stergeButton;
    private JTextArea textArea1;
    private JLabel numeEvenimentLabel;
    private JLabel adaugaNotitaLabel;
    private JButton adaugaButton;
    private JButton actualizareListaButton;
    private Eveniment eveniment;
    private DefaultListModel<Notita> model;

    public NotitaFrame(Eveniment eveniment) {
        this.eveniment = eveniment;
        model = new DefaultListModel<>();
        listaNotite.setModel(model);
        afisareNotite();
        actualizareListaButton.addActionListener(ev4->afisareNotite());
        adaugaButton.addActionListener(ev1 -> adaugaNotita());
        stergeButton.addActionListener(ev2 -> stergeNotita());
        modificaButton.addActionListener(ev3 -> modificaNotita());
        numeEvenimentLabel.setText(eveniment.getNume().concat(" ").concat(eveniment.getData().toString()));
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void afisareNotite() {
        List<Notita> notite = NotitaController.getInstance().findByEvent(eveniment.getId());
        model.clear();
        notite.forEach(model::addElement);
    }

    private void adaugaNotita() {
        String continut = textArea1.getText();
        Notita notita = new Notita.Builder()
                .setId(0)
                .setContinut(continut)
                .setEvenimentId(eveniment.getId())
                .build();
        boolean rez = NotitaController.getInstance().create(notita);
        if (rez) {
            afisareNotite();
        } else {
            JOptionPane.showMessageDialog(null, "Nu s-a putut adauga notita");
        }
        textArea1.setText("");
    }

    private void stergeNotita() {
        Optional<Notita> selected = Optional.ofNullable((Notita) listaNotite.getSelectedValue());
        boolean rez = selected.filter(notita->NotitaController.getInstance().delete(notita.getId())).isPresent();
        if (rez) {
            afisareNotite();
        }else{
            JOptionPane.showMessageDialog(null, "Nu s-a putut sterge notita. Va rugam selectati o notita din lista.");
        }
    }

    private void modificaNotita() {
        Optional<Notita> selected = Optional.ofNullable((Notita) listaNotite.getSelectedValue());
        selected.ifPresent(ModificaNotitaFrame::new);
        if(selected.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nu s-a putut modifica notita. Va rugam selectati o notita din lista.");
        }
        afisareNotite();
    }
}
