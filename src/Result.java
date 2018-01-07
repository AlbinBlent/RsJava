
public class Result {
	
	private int participantStartNubmber;
	private String eventName;
	private double result;
	
	public Result(int participantStartNubmber, String eventName, double result){
		this.participantStartNubmber = participantStartNubmber;
		this.eventName = eventName;
		this.result = result;
	}
	
	public int getParticipantStartNubmber() {
		return participantStartNubmber;
	}
	public void setParticipantStartNubmber(int participantStartNubmber) {
		this.participantStartNubmber = participantStartNubmber;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	
}
