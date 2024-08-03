import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RubricaTableModel extends AbstractTableModel {
    private final String[] columnNames = { "Nome", "Cognome", "Telefono" };
    private final List<Persona> persone;

    public RubricaTableModel(List<Persona> persone) {
        this.persone = persone;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return persone.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Persona persona = persone.get(row);
        switch (col) {
            case 0:
                return persona.getNome();
            case 1:
                return persona.getCognome();
            case 2:
                return persona.getTelefono();
            default:
                return null;
        }
    }

    public Persona getPersonaAt(int row) {
        return persone.get(row);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void addPersona(Persona persona) {
        this.persone.add(persona);
        fireTableRowsInserted(persone.size() - 1, persone.size() - 1);
        //RubricaIO.addPersona(persona);
        RubricaDB.addPersona(persona);
    }

    public void removePersona(int row, Persona persona) {
        this.persone.remove(persona);
        fireTableRowsDeleted(row, row);
        //RubricaIO.deletePersona(persona);
        RubricaDB.deletePersona(persona);

    }

    public void updatePersona(int row, Persona persona) {
        this.persone.set(row, persona);
        fireTableRowsUpdated(row, row);
        //RubricaIO.updatePersona(persona);
        RubricaDB.updatePersona(persona);
    }    
}
