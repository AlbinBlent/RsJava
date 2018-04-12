//Rosanna Sjöö, rosj4727, grupp 078
//inmatning ska avbrytas efter att två likadana events skrivits in. Gå tillbaka till command, inte fråga efter event name igen
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event {
	
	private String eventName;
	private int attemptsAllowed;
	private ArrayList<Result> eventResults;
	
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
		ArrayList<Result> resultsToBeRemoved = new ArrayList<>();
		for (Result eventResult : eventResults){
			if (eventResult.getParticipant().getStartNumber() == participantStartNumber) {
				resultsToBeRemoved.add(eventResult);
			}
		}

		for (Result eventResult : resultsToBeRemoved) {
			eventResults.remove(eventResult);
		}
	}

	public void addResult(Participant participant, double result){
		eventResults.add(new Result(this, participant, result));
	}

	/*
        Sorterar resultaten i alphabetisk ordning så att alla med samma resultat är sorterade i fallade alphabetisk ordning
     */

	private ArrayList<Result> sortResultsByParticipantNames(ArrayList<Result> results) {
		ArrayList<Result> sortedResults = new ArrayList<>();
		for (Result result : results){
			int index = 0;
			Participant participant = result.getParticipant();
			String firstAndLastname = participant.getForename() + participant.getSurname();
			for (Result sortedResult : sortedResults){
				Participant sortedParticipant = sortedResult.getParticipant();
				String firstAndLastnameSorted = sortedParticipant.getForename() + sortedParticipant.getSurname();
				if (firstAndLastname.toLowerCase().compareTo(firstAndLastnameSorted.toLowerCase()) < 0) {
					index++;
				}
			}
			sortedResults.add(index, result);
		}
		return sortedResults;
	}

	/*
    Sorterar score
	 */
	private ArrayList<Result> sortResultsByScore(ArrayList<Result> results) {
		ArrayList<Result> sortedResults = new ArrayList<>();
		for (Result result : results){
			int index = 0;
			for (Result sortResult : sortedResults){
				if (result.getScore() < sortResult.getScore()) {
					index++;
				}
			}
			sortedResults.add(index, result);
		}
		return sortedResults;
	}



	public void printResults(){

		ArrayList<Result> eventResultsSortedAlphabetically = sortResultsByParticipantNames(eventResults);
		ArrayList<Result> eventResultsSortedByResult = sortResultsByScore(eventResultsSortedAlphabetically);

		/*
			Ta bort resultat för deltagare med fler än ett resultat och spara det bästa.
			Eftersom att listan redan är sorterad med bästa resultatet först så kommer det bästa sparas
		 */
		ArrayList<Result> eventResultsSortedByResultWithUniqueParticipants = new ArrayList<>();
		for (Result result : eventResultsSortedByResult){
			if (!participantExistsInResultList(result.getParticipant().getStartNumber(), eventResultsSortedByResultWithUniqueParticipants)){
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
			Result eventResult = eventResultsSortedByResultWithUniqueParticipants.get(i);
			Participant participant = eventResult.getParticipant();
			if (i == 0) {
				System.out.println(previousPosition + " " + eventResult.getScore() + " " + participant.getForename() + " " + participant.getSurname());
				previousResult = eventResult.getScore();
			} else if (i < listSize){
				if (eventResult.getScore() == previousResult) {
					System.out.println(previousPosition + " " + eventResult.getScore() + " " + participant.getForename() + " " + participant.getSurname());
				} else {
				    previousPosition = (i + 1);
				    previousResult = eventResult.getScore();
					System.out.println(previousPosition + " " + eventResult.getScore() + " " + participant.getForename() + " " + participant.getSurname());
				}
			}
		}
	}

	private boolean participantExistsInResultList(int startNumber, ArrayList<Result> eventResults){
		for (Result eventResult : eventResults){
			if (eventResult.getParticipant().getStartNumber() == startNumber){
				return true;
			}
		}
		return false;
	}

	public int getAmountOfAttemptsForParticipant(int participantStartNumber){
		int amountOfAttempts = 0;
		for (Result eventResult : eventResults){
			if (eventResult.getParticipant().getStartNumber() == participantStartNumber) {
				amountOfAttempts++;
			}
		}
		return amountOfAttempts;
	}
}