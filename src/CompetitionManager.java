import java.util.ArrayList;

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
		    removeParticipantFromResults(startNumber);
			participants.remove(remove);
			return true;
		}
	}

	/*
	 	Jag antog att removeParticipant också borde ta bort alla resultat som den deltagaren har??
	 */
	private void removeParticipantFromResults(int participantStartNumber){
		ArrayList<Result> resultsToBeRemoved = new ArrayList<>();
	    for (Result result : results){
	    	if (result.getParticipantStartNubmber() == participantStartNumber){
	    		resultsToBeRemoved.add(result);
			}
		}

		for (Result result : resultsToBeRemoved) {
	    	results.remove(result);
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
	
	public void addEvent(String eventName, int attemptsAllowed){
		/*
			Visst var det så att flera event inte fick ha samma namn?
		 */
		for (Event event : events){
			if (event.getEventName().equals(eventName)){
				return;
			}
		}
		Event event = new Event(eventName, attemptsAllowed);
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
	    /*
	    	Plocka ut resultst som bara har med detta event att göra
	     */
		ArrayList<Result> resultsFromEvent = new ArrayList<>();
		for (Result result : results){
			if (result.getEventName().equals(eventName)){
				resultsFromEvent.add(result);
			}
		}

		/*
			Sortera resultaten i alphabetisk ordning
			(så att alla med samma resultat är sorterade i fallade alphabetisk ordning)
		 */
		ArrayList<Result> resultsSortedAlphabetically = new ArrayList<>();
		for (Result result : resultsFromEvent){
			int index = 0;
			Participant participant = getParticipant(result.getParticipantStartNubmber());
			String firstAndLastname = participant.getForename() + participant.getSurname();
			for (Result sortResult : resultsSortedAlphabetically){
				Participant sortedParticipant = getParticipant(sortResult.getParticipantStartNubmber());
				String firstAndLastnameSorted = sortedParticipant.getForename() + sortedParticipant.getSurname();
				if (firstAndLastname.toLowerCase().compareTo(firstAndLastnameSorted.toLowerCase()) < 0) {
					index++;
				}
			}
			resultsSortedAlphabetically.add(index, result);
		}

		/*
			Sortera resultaten efter bäst resultat
		 */
		ArrayList<Result> resultsSortedByResult = new ArrayList<>();
		for (Result result : resultsSortedAlphabetically){
			int index = 0;
			for (Result sortResult : resultsSortedByResult){
				if (result.getResult() < sortResult.getResult()) {
					index++;
				}
			}
			resultsSortedByResult.add(index, result);
		}

		/*
			Skriv ut resultaten
		 */
		printList(resultsSortedByResult);
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
