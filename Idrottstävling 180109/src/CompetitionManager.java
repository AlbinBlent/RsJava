//Rosanna Sjöö, rosj4727, grupp 078
import java.util.ArrayList;

public class CompetitionManager {
	
	private ArrayList<Participant> participants;
	private ArrayList<Event> events;
	private int startNumber;

	public CompetitionManager(){
		this.participants = new ArrayList<>();
		this.events = new ArrayList<>();
		this.startNumber = 99;
	}
	
	public int addParticipant(String forename, String surname, String teamName){ //lagt till efter handledning
		startNumber++;
		Participant participant = new Participant(startNumber, forename, surname, teamName);
		participants.add(participant);
		return startNumber;
	}

	public Participant removeParticipant(int startNumber){
		Participant remove = null;

		for (Participant participant : participants){
			if (participant.getStartNumber() == startNumber){
				remove = participant;
			}
		}
		if (remove == null){
			return null;
		}else{
		    removeParticipantFromEventResults(startNumber);
			participants.remove(remove);
			return remove;
		}
	}

	public void addResult(int startNumber, String eventName, double result){
		Participant participant = null;
		Event event = null;
		for (Participant p : participants){
			if (p.getStartNumber() == startNumber){
				participant = p;
			}
		}

		for (Event e : events){
			if (e.getEventName().equals(eventName)){
				event = e;
			}
		}
		participant.addResult(event, result);
		event.addResult(participant, result);
	}

	private void removeParticipantFromEventResults(int startNumber){
		for (Event event : events){
			event.removeParticipantResults(startNumber);
		}
	}

	public Participant getParticipant(int startNumber){
		for (Participant participant : participants){
			if (participant.getStartNumber() == startNumber){
				return participant;
			}
		}
		return null;
	}

	public void addEvent(String eventName, int attemptsAllowed){
        if (checkIfEventExists(eventName)){
            return;
		}
		Event event = new Event(eventName, attemptsAllowed);
		events.add(event);
	}

	public int getAmountOfAttemptsForParticipantOnEvent(int participantStartNumber, String eventName){
		for (Event event : events){
			if (event.getEventName().equals(eventName)){
				return event.getAmountOfAttemptsForParticipant(participantStartNumber);
			}
		}
		return 0;
	}

	public boolean checkIfEventExists(String eventName) {
	    for (Event event : events) {
			if (event.getEventName().equals(eventName)){
				return true;
			}
		}
		return false;
	}

	public Event getEvent(String eventName) {
		for (Event event : events) {
			if (event.getEventName().equals(eventName)) {
				return event;
			}
		}
		return null;
	}

	public void printResultForParticipant(int participantStartNumber){
	    if (getParticipant(participantStartNumber) == null) {
			System.out.println("Error: no participant with number " + participantStartNumber);
			return;
		}

		for (Participant participant : participants) {
	    	if (participant.getStartNumber() == participantStartNumber){
	    		participant.printResults();
			}
		}
	}
	
	public void printResultForEvent(String eventName){
	    for (Event event : events){
	    	if (event.getEventName().equals(eventName)){
	    		event.printResults();
			}
		}
	}
}