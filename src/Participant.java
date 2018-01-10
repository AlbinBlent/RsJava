import java.util.ArrayList;

public class Participant {
	
	private int startNumber;
	private String forename;
	private String surname;
	private ParticipantResult[] participantResults;
	private int resultIndex;

	public Participant(int startNumber, String forename, String surname){
		this.startNumber = startNumber;
		this.forename = forename;
		this.surname = surname;
		this.participantResults = new ParticipantResult[100];
		resultIndex = 0;
	}
	
	public int getStartNumber() {
		return startNumber;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public void addResult(String eventName, double result){
		participantResults[resultIndex] = new ParticipantResult(eventName, result);
		resultIndex++;
	}

	public void printResults(){
		ArrayList<ParticipantResult> resultsSortedByEvent = new ArrayList<>();
		for (int i = 0; i < resultIndex; i++) {
			ParticipantResult result = participantResults[i];
			int index = 0;
			String eventName = result.eventName;
			for (ParticipantResult sortResult : resultsSortedByEvent){
				String sortedEventName = sortResult.eventName;
				if (eventName.toLowerCase().compareTo(sortedEventName.toLowerCase()) > 0) {
					index++;
				}
			}
			resultsSortedByEvent.add(index, result);
		}

		for (ParticipantResult participantResult : resultsSortedByEvent){
			System.out.println("Result for " + forename + " " + surname + " in "
					+ participantResult.eventName + ": " + participantResult.result);
		}
	}

	private class ParticipantResult{
		private String eventName;
		private double result;

		private ParticipantResult(String eventName, double result){
			this.eventName = eventName;
			this.result = result;
		}
	}
}
