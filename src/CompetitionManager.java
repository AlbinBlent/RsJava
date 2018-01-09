import java.util.ArrayList;

public class CompetitionManager {
	
	private ArrayList<Participant> participants;
	private ArrayList<Event> events;
	private ResultService resultService;

	public CompetitionManager(ResultService resultService){
		this.participants = new ArrayList<>();
		this.events = new ArrayList<>();
		this.resultService = resultService;
	}
	
	public int addParticipant(String forename, String surname){
		int startNumber = 100 + participants.size();
		Participant participant = new Participant(startNumber, forename, surname);
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
		    resultService.removeParticipantFromResults(startNumber);
			participants.remove(remove);
			return remove;
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

	public boolean addEvent(String eventName, int attemptsAllowed){
		/*
			Visst var det så att flera event inte fick ha samma namn?
		 */
        if (checkIfEventExists(eventName)){
            return false;
		}
		Event event = new Event(eventName, attemptsAllowed);
		events.add(event);
		return true;
	}

	public boolean checkIfEventExists(String eventName) {
	    for (Event event : events) {
			System.out.println(" --- eventName: " + eventName);
			System.out.println(" --- event.getEventName(): " + event.getEventName());
			if (event.getEventName().equals(eventName)){
				System.out.println("TRUE");
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
		ArrayList<Result> resultForParticipant = resultService.getResultForParticipant(participantStartNumber);

		/*
			Sortera resultaten i alphabetisk ordning
		*/
		ArrayList<Result> resultsSortedAlphabetically = new ArrayList<>();
		for (Result result : resultForParticipant){
			int index = 0;
			String eventName = result.getEventName();
			for (Result sortResult : resultsSortedAlphabetically){
				String sortedEventName = sortResult.getEventName();
				if (eventName.toLowerCase().compareTo(sortedEventName.toLowerCase()) > 0) {
					index++;
				}
			}
			resultsSortedAlphabetically.add(index, result);
		}

		// Print list
		Participant participant = getParticipant(participantStartNumber);
		String firstAndLastName = participant.getForename() + " " + participant.getSurname();
        for (Result result : resultsSortedAlphabetically) {
			System.out.println("Result for " + firstAndLastName + " in " + result.getEventName() + ": " + result.getResult());
		}
	}
	
	public void printResultForEvent(String eventName){
	    /*
	    	Plocka ut resultst som bara har med detta event att göra
	     */
		ArrayList<Result> resultsFromEvent = resultService.getResultForEvent(eventName);

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
			Ta bort resultat för deltagare med fler än ett resultat. Spara det bästa.
			Efter som att listan redan är sorterad med bästa resultatet först så kommer det bästa sparas
		 */
		ArrayList<Result> resultsSortedByResultWithUniqueParticipants = new ArrayList<>();
		for (Result result : resultsSortedByResult){
		    if (!participantExistsInResultList(result.getParticipantStartNubmber(), resultsSortedByResultWithUniqueParticipants)){
				resultsSortedByResultWithUniqueParticipants.add(result);
			}
		}

		/*
			Skriv ut resultaten
		 */
		int previousPosition = 1;
		double previousResult = 0;
		int listSize = resultsSortedByResultWithUniqueParticipants.size();
		for (int i = 0; i < listSize; i++) {
			Result result = resultsSortedByResultWithUniqueParticipants.get(i);
			Participant participant = getParticipant(result.getParticipantStartNubmber());
			if (i == 0) {
				System.out.println(previousPosition + " " + result.getResult() + " " + participant.getForename() + " " + participant.getSurname());
				previousResult = result.getResult();
			} else if (i < listSize){
				if (result.getResult() == previousResult) {
					System.out.println(previousPosition + " " + result.getResult() + " " + participant.getForename() + " " + participant.getSurname());
				} else {
				    previousPosition = (i + 1);
				    previousResult = result.getResult();
					System.out.println(previousPosition + " " + result.getResult() + " " + participant.getForename() + " " + participant.getSurname());
				}
			}
		}
	}

	private boolean participantExistsInResultList(int participantStartNumber, ArrayList<Result> list) {
		for (Result result : list) {
			if (result.getParticipantStartNubmber() == participantStartNumber) {
				return true;
			}
		}
		return false;
	}
}
