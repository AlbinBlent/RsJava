import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;

public class CompetitionManager {
	
	private ArrayList<Participant> participants;
	private ArrayList<Event> events;
	private ArrayList<Result> results;
	
	public CompetitionManager(){
		this.participants = new ArrayList<>();
		this.events = new ArrayList<>();
		this.results = new ArrayList<>();
	}
	
	public void addParticipant(String forename, String surname){
		int startNumber = 100 + participants.size();
		Participant participant = new Participant(startNumber, forename, surname);
		participants.add(participant);
	}
	
	public boolean removeParticipant(int startNumber){
		Participant remove = null;
		
		for (Participant participant : participants){
			if (participant.getStartNumber() == startNumber){
				remove = participant;
			}
		}
		if (remove == null){
			return false;
		}else{
			participants.remove(remove);
			return true;
		}
	}
	
	private Participant getParticipant(int startNumber){
		for (Participant participant : participants){
			if (participant.getStartNumber() == startNumber){
				return participant;
			}
		}
		return null;
	}
	
	public void addEvent(String name, int attemptsAllowed){
		Event event = new Event(name, attemptsAllowed);
		events.add(event);
	}
	
	public void addResult(int participantStartNubmber, String eventName, double res){
		Result result = new Result(participantStartNubmber, eventName, res);
		results.add(result);
	}
	
	public void printResultForParticipant(int participantStartNubmber){
		for (Result result : results){
			if (result.getParticipantStartNubmber() == participantStartNubmber){
				System.out.println(result.getEventName() + " " + result.getResult());
			}
		}
	}
	
	public void printResultForEvent(String eventName){
		ArrayList<Result> resultsFromEvent = new ArrayList<>();
		for (Result result : results){
			if (result.getEventName().equals(eventName)){
				resultsFromEvent.add(result);
			}
		}

		printList(results);
		
		printList(resultsFromEvent);
		
		ArrayList<Result> sortedResultsFromEvent = new ArrayList<>();
		for (Result result : resultsFromEvent){
			int index = 0;
			for (Result sortResult : sortedResultsFromEvent){
				if (result.getResult() < sortResult.getResult()) {
					index++;
				}
			}
			sortedResultsFromEvent.add(index, result);
		}
		
		printList(sortedResultsFromEvent);
		
		
	}
	
	private void printList(ArrayList<Result> results){
		System.out.println(" - - - - Printing - - - - ");
		for (Result sortResult : results){
			Participant participant = getParticipant(sortResult.getParticipantStartNubmber());
			System.out.println(participant.getForename() + " " 
					+ participant.getSurname() + " " 
					+ sortResult.getResult());
		}
		System.out.println(" - - - - Stop Printing - - - - ");
	}
	
}
