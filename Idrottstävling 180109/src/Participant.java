//Rosanna Sjöö, rosj4727, grupp 078
import java.util.ArrayList;

public class Participant {
	
	private int startNumber;
	private String forename;
	private String surname;
	private Result[] participantResults;
	private int resultIndex;

	public Participant(int startNumber, String forename, String surname){
		this.startNumber = startNumber;
		this.forename = forename;
		this.surname = surname;
		this.participantResults = new Result[100];
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

	public void addResult(Event event, double result){
		participantResults[resultIndex] = new Result(event, this, result);
		resultIndex++;
	}

	/**
	 * Sort results in descending alphabetical order by event name
	 * @param results unsorted array
	 * @return sorted list
	 */
	private ArrayList<Result> sortResultsByEventName(Result[] results) {
		ArrayList<Result> sortedResults = new ArrayList<>();
		for (int i = 0; i < resultIndex; i++) {
			Result result = results[i];
			int index = 0;
			String eventName = result.getEvent().getEventName();
			for (Result sortResult : sortedResults){
				String sortedEventName = sortResult.getEvent().getEventName();
				if (eventName.toLowerCase().compareTo(sortedEventName.toLowerCase()) > 0) {
					index++;
				}
			}
			sortedResults.add(index, result);
		}
		return sortedResults;
	}

	public void printResults(){
		ArrayList<Result> resultsSortedByEvent = sortResultsByEventName(participantResults);
		for (Result participantResult : resultsSortedByEvent){
			System.out.println("Result for " + forename + " " + surname + " in "
					+ participantResult.getEvent().getEventName() + ": " + participantResult.getScore());
		}
	}
}