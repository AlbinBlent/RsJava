
public class Event {
	
	private String eventName;
	private int attemptsAllowed;
	
	public Event(String eventName, int attemptsAllowed){
		this.eventName = eventName;
		this.attemptsAllowed = attemptsAllowed;
	}
	
	public int getAttemptsAllowed() {
		return attemptsAllowed;
	}
	public void setAttemptsAllowed(int attemptsAllowed) {
		this.attemptsAllowed = attemptsAllowed;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	
	

}
