
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
	public String getEventName() {
		return eventName;
	}
	public double getResult() {
		return result;
	}
}
