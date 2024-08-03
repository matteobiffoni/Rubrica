import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RubricaDB {
    public static ArrayList<Persona> retrieveInformazioni() {
        ArrayList<Persona> result = new ArrayList<>();
        String sql = "SELECT * FROM persone";
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                int eta = rs.getInt("eta");
                Persona toAdd = new Persona(id, nome, cognome, indirizzo, telefono, eta);
                result.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public static void addPersona(Persona persona) {
        String sql = "INSERT INTO persone (id, nome, cognome, indirizzo, telefono, eta, created_by, ts_created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, persona.getID());
            ps.setString(2, persona.getNome());
            ps.setString(3, persona.getCognome());
            ps.setString(4, persona.getIndirizzo());
            ps.setString(5, persona.getTelefono());
            ps.setInt(6, persona.getEta());
            ps.setInt(7, Utente.currentUser.getUid());
            ps.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updatePersona(Persona persona) {
        String sql = "UPDATE persone SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ?, last_updated_by = ?, ts_last_updated = ? WHERE id = ?";
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, persona.getNome());
            ps.setString(2, persona.getCognome());
            ps.setString(3, persona.getIndirizzo());
            ps.setString(4, persona.getTelefono());
            ps.setInt(5, persona.getEta());
            ps.setInt(6, Utente.currentUser.getUid());
            ps.setTimestamp(7, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.setString(8, persona.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deletePersona(Persona persona) {
        String sql = "DELETE FROM persone WHERE id = ?";
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, persona.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static int login(String username, String password) {
        String sql = "SELECT * FROM utenti WHERE username = ? AND password = ?";
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("uid");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
}
