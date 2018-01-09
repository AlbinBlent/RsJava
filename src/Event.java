
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
	public String getEventName() {
		return eventName;
	}
}
