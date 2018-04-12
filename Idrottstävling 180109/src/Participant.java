//Rosanna Sjöö, rosj4727, grupp 078
//lägg in bara som string ett team-namn för alla deltagare. Ändra i to-stringen också
import java.util.ArrayList;
import java.util.Arrays;

public class Participant {
	
	private int startNumber;
	private String forename;
	private String surname;
	private ArrayList<Result> participantResults;
	private String teamName; 

	public Participant(int startNumber, String forename, String surname, String teamName){
		this.startNumber = startNumber;
		this.forename = forename;
		this.surname = surname;
		this.teamName = teamName; 
		this.participantResults = new ArrayList<>();
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
	public String getTeamName() { 
		return teamName;
	}

	public void addResult(Event event, double result){
		participantResults.add(new Result(event, this, result));
	}

	private ArrayList<Result> sortResultsByEventName(ArrayList<Result> results) {
		//ArrayList<Result> sortedResults = new ArrayList<>();
		Result[] sortedResults = results.toArray(new Result[results.size()]);
		
		for (Result result : results) {
			int index = 0;
			String eventName = result.getEvent().getEventName();
			Result[] temp = new Result[results.size()];
			
			for (int i = 0; i < sortedResults.length; i++) {
				Result sortedResult = sortedResults[i];
				String sortedEventName = sortedResult.getEvent().getEventName();
				if (eventName.toLowerCase().compareTo(sortedEventName.toLowerCase()) > 0) {
					temp[i] = sortedResult;
				} else {
					temp[i] = sortedResult;
					i++;
				}
			
				/*
				String sortedEventName = sortedResult.getEvent().getEventName();
				if (eventName.toLowerCase().compareTo(sortedEventName.toLowerCase()) > 0) {
					index++;
				}
				*/
			}
			sortedResults = temp;
			//sortedResults.add(index, result);
		}
		return new ArrayList<>(Arrays.asList(sortedResults));
	}

	public void printResults(){
		ArrayList<Result> resultsSortedByEvent = sortResultsByEventName(participantResults);
		for (Result participantResult : resultsSortedByEvent){
			System.out.println("Result for " + forename + " " + surname + " in "
					+ participantResult.getEvent().getEventName() + ": " + participantResult.getScore());
		}
	}
}