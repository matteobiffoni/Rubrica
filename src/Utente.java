
public class Utente {
	public static Utente currentUser;
	private int uid;
	private String username;
	private String password;

	public Utente(int uid, String username, String password) {
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
