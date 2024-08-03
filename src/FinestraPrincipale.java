import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraPrincipale extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;

    private ButtonsActionListener buttonsActionListener;

    private JButton nuovoButton;
    private JButton modificaButton;
    private JButton eliminaButton;
    private ImageIcon nuovoIcon;
    private ImageIcon modificaIcon;
    private ImageIcon eliminaIcon;

    private JToolBar toolbar;
    private JPanel toolbarPanel;

    public FinestraPrincipale(RubricaTableModel rubricaTableModel) {
        this.setLayout(new BorderLayout());

        table = new JTable(rubricaTableModel);

        toolbar = new JToolBar();
        toolbarPanel = new JPanel();

        nuovoIcon = new ImageIcon("images/plus.gif", "plus icon created by \"kliwir art\" downloaded from https://flaticon.com");
        modificaIcon = new ImageIcon("images/pencil.gif", "edit icon created by \"Freepik\" downloaded from https://flaticon.com");
        eliminaIcon = new ImageIcon("images/delete.gif", "delete icon created by \"uniconlabs\" downloaded from https://flaticon.com");

        nuovoButton = new JButton("Nuovo", nuovoIcon);
        modificaButton = new JButton("Modifica", modificaIcon);
        eliminaButton = new JButton("Elimina", eliminaIcon);
        buttonsActionListener = new ButtonsActionListener(this, rubricaTableModel, table, nuovoButton, modificaButton, eliminaButton);

        nuovoButton.addActionListener(buttonsActionListener);
        modificaButton.addActionListener(buttonsActionListener);
        eliminaButton.addActionListener(buttonsActionListener);
        
        toolbarPanel.add(nuovoButton);
        toolbarPanel.add(modificaButton);
        toolbarPanel.add(eliminaButton);

        toolbar.add(toolbarPanel);
        this.add(toolbar, BorderLayout.NORTH);


        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setTitle("Rubrica");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}


class ButtonsActionListener implements ActionListener {
    private FinestraPrincipale owner;
    private RubricaTableModel rubricaTableModel;
    private JTable table;
    private JButton create, modify, delete;

    public ButtonsActionListener(FinestraPrincipale owner, RubricaTableModel rubricaTableModel, JTable table, JButton createButton, JButton modifyButton, JButton deleteButton) {
        this.owner = owner;
        this.rubricaTableModel = rubricaTableModel;
        this.table = table;
        this.create = createButton;
        this.modify = modifyButton;
        this.delete = deleteButton;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton caller = (JButton) e.getSource();
        if (caller == create) {
            new EditorPersona(owner, null, rubricaTableModel, -1);
        }
        else if (caller == modify) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(table, "Per effettuare una modifica è necessario prima selezionare una persona", "Impossibile effettuare la modifica", JOptionPane.ERROR_MESSAGE);
            }
            else {
                new EditorPersona(owner, rubricaTableModel.getPersonaAt(selectedRow), rubricaTableModel, selectedRow);
            }
        }
        else if (caller == delete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(table, "Per effettuare una eliminazione è necessario prima selezionare una persona", "Impossibile effettuare l'eliminazione", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String nome = (String) rubricaTableModel.getValueAt(selectedRow, 0);
                String cognome = (String) rubricaTableModel.getValueAt(selectedRow, 1);
                int res = JOptionPane.showConfirmDialog(table, "Eliminare la persona " + nome + " " + cognome + "?", "Eliminazione persona", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    rubricaTableModel.removePersona(selectedRow, rubricaTableModel.getPersonaAt(selectedRow));
                }
            }
        }
    }
    
}
