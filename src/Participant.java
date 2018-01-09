
public class Participant {
	
	private int startNumber;
	private String forename;
	private String surname;
	
	public Participant(int startNumber, String forename, String surname){
		this.startNumber = startNumber;
		this.forename = forename;
		this.surname = surname;
	}
	
	public int getStartNumber() {
		return startNumber;
	}
	public String getForename() {
		return forename;
	}
	public String getSurname() {
		return surname;
	}
}
