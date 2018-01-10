import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event {
	
	private String eventName;
	private int attemptsAllowed;
	private ArrayList<EventResult> eventResults;
	
	public Event(String eventName, int attemptsAllowed){
		this.eventName = eventName;
		this.attemptsAllowed = attemptsAllowed;
		this.eventResults = new ArrayList<>();
	}
	
	public int getAttemptsAllowed() {
		return attemptsAllowed;
	}

	public String getEventName() {
		return eventName;
	}

	public void removeParticipantResults(int participantStartNumber){
		ArrayList<EventResult> resultsToBeRemoved = new ArrayList<>();
		for (EventResult eventResult : eventResults){
			if (eventResult.participant.getStartNumber() == participantStartNumber) {
				resultsToBeRemoved.add(eventResult);
			}
		}

		for (EventResult eventResult : resultsToBeRemoved) {
			eventResults.remove(eventResult);
		}
	}

	public void addResult(Participant participant, double result){
		eventResults.add(new EventResult(participant, result));
	}

	public void printResults(){

		/*
			Sortera resultaten i alphabetisk ordning
			(så att alla med samma resultat är sorterade i fallade alphabetisk ordning)
		 */
		ArrayList<EventResult> eventResultsSortedAlphabetically = new ArrayList<>();
		for (EventResult eventResult : eventResults){
			int index = 0;
			Participant participant = eventResult.participant;
			String firstAndLastname = participant.getForename() + participant.getSurname();
			for (EventResult sortedEventResult : eventResultsSortedAlphabetically){
				Participant sortedParticipant = sortedEventResult.participant;
				String firstAndLastnameSorted = sortedParticipant.getForename() + sortedParticipant.getSurname();
				if (firstAndLastname.toLowerCase().compareTo(firstAndLastnameSorted.toLowerCase()) < 0) {
					index++;
				}
			}
			eventResultsSortedAlphabetically.add(index, eventResult);
		}

		/*
			Sortera resultaten efter bäst resultat
		 */
		ArrayList<EventResult> eventResultsSortedByResult = new ArrayList<>();
		for (EventResult result : eventResultsSortedAlphabetically){
			int index = 0;
			for (EventResult sortResult : eventResultsSortedByResult){
				if (result.result < sortResult.result) {
					index++;
				}
			}
			eventResultsSortedByResult.add(index, result);
		}

		/*
			Ta bort resultat för deltagare med fler än ett resultat. Spara det bästa.
			Efter som att listan redan är sorterad med bästa resultatet först så kommer det bästa sparas
		 */
		ArrayList<EventResult> eventResultsSortedByResultWithUniqueParticipants = new ArrayList<>();
		for (EventResult result : eventResultsSortedByResult){
			if (!participantExistsInResultList(result.participant.getStartNumber(), eventResultsSortedByResultWithUniqueParticipants)){
				eventResultsSortedByResultWithUniqueParticipants.add(result);
			}
		}

		/*
			Skriv ut resultaten
		 */
		int previousPosition = 1;
		double previousResult = 0;
		int listSize = eventResultsSortedByResultWithUniqueParticipants.size();
		for (int i = 0; i < listSize; i++) {
			EventResult eventResult = eventResultsSortedByResultWithUniqueParticipants.get(i);
			Participant participant = eventResult.participant;
			if (i == 0) {
				System.out.println(previousPosition + " " + eventResult.result + " " + participant.getForename() + " " + participant.getSurname());
				previousResult = eventResult.result;
			} else if (i < listSize){
				if (eventResult.result == previousResult) {
					System.out.println(previousPosition + " " + eventResult.result + " " + participant.getForename() + " " + participant.getSurname());
				} else {
				    previousPosition = (i + 1);
				    previousResult = eventResult.result;
					System.out.println(previousPosition + " " + eventResult.result + " " + participant.getForename() + " " + participant.getSurname());
				}
			}
		}
	}

	private boolean participantExistsInResultList(int startNumber, ArrayList<EventResult> eventResults){
		for (EventResult eventResult : eventResults){
			if (eventResult.participant.getStartNumber() == startNumber){
				return true;
			}
		}
		return false;
	}

	public int getAmountOfAttemptsForParticipant(int participantStartNumber){
		int amountOfAttempts = 0;
		for (EventResult eventResult : eventResults){
			if (eventResult.participant.getStartNumber() == participantStartNumber) {
				amountOfAttempts++;
			}
		}
		return amountOfAttempts;
	}

	private class EventResult {
		private Participant participant;
		private double result;

		private EventResult(Participant participant, double result){
		    this.participant = participant;
		    this.result = result;
		}
	}
}
