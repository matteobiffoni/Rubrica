import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class EditorPersona extends JDialog {
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel cognomeLabel;
    private JTextField cognomeField;
    private JLabel indirizzoLabel;
    private JTextField indirizzoField;
    private JLabel telefonoLabel;
    private JTextField telefonoField;
    private JLabel etaLabel;
    private JTextField etaField;
    private JPanel formPanel;
    private JToolBar toolBar;
    private JPanel buttonsPanel;
    private ImageIcon saveIcon;
    private JButton saveButton;
    private ImageIcon cancelIcon;
    private JButton cancelButton;

    public EditorPersona(FinestraPrincipale parent, Persona persona, RubricaTableModel rubricaTableModel, int selectedRow) {
        super(parent, persona == null ? "Creazione" : "Modifica");

        this.setLayout(new BorderLayout());
        
        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        nomeLabel = new JLabel("Nome");
        cognomeLabel = new JLabel("Cognome");
        indirizzoLabel = new JLabel("Indirizzo");
        telefonoLabel = new JLabel("Telefono");
        etaLabel = new JLabel("Età");
        nomeField = new JTextField((String) (persona == null ? "" : persona.getNome()),15);
        nomeField.setHorizontalAlignment(JTextField.RIGHT);
        cognomeField = new JTextField((String) (persona == null ? "" : persona.getCognome()),15);
        cognomeField.setHorizontalAlignment(JTextField.RIGHT);
        indirizzoField = new JTextField((String) (persona == null ? "" : persona.getIndirizzo()),15);
        indirizzoField.setHorizontalAlignment(JTextField.RIGHT);
        telefonoField = new JTextField((String) (persona == null ? "" : persona.getTelefono()),15);
        telefonoField.setHorizontalAlignment(JTextField.RIGHT);
        etaField = new JTextField((String) (persona == null || persona.getEta() == -1 ? "" : String.valueOf(persona.getEta())),15);
        etaField.setHorizontalAlignment(JTextField.RIGHT);
        formPanel.add(nomeLabel);
        formPanel.add(nomeField);
        formPanel.add(cognomeLabel);
        formPanel.add(cognomeField);
        formPanel.add(indirizzoLabel);
        formPanel.add(indirizzoField);
        formPanel.add(telefonoLabel);
        formPanel.add(telefonoField);
        formPanel.add(etaLabel);
        formPanel.add(etaField);
        
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        saveIcon = new ImageIcon("images/diskette.gif", "save icon created by \"Freepik\" downloaded from https://flaticon.com");
        saveButton = new JButton("Salva", saveIcon);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText().trim();
                String cognome = cognomeField.getText().trim();
                String indirizzo = indirizzoField.getText().trim();
                String telefono = telefonoField.getText().trim();
                int eta = -1;
                try {
                    eta = Integer.parseInt(etaField.getText().trim());
                }
                catch (Exception ex) {
                    eta = -1;
                }
                if (persona == null) {
                    rubricaTableModel.addPersona(new Persona(UUID.randomUUID().toString(), nome, cognome, indirizzo, telefono, eta));
                }
                else {
                    persona.setNome(nome);
                    persona.setCognome(cognome);
                    persona.setIndirizzo(indirizzo);
                    persona.setTelefono(telefono);
                    persona.setEta(eta);
                    rubricaTableModel.updatePersona(selectedRow, persona);
                }
                dispose();
            }
        });
        cancelIcon = new ImageIcon("images/back.gif", "back icon created by \"Freepik\" downloaded from https://flaticon.com");
        cancelButton = new JButton("Annulla", cancelIcon);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonsPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.add(formPanel, BorderLayout.CENTER);
        toolBar = new JToolBar();
        toolBar.add(buttonsPanel);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setModal(true);
		this.getRootPane().setDefaultButton(saveButton);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
}
