import java.util.Scanner;


public class Program {

	private ResultService resultService;
	private CompetitionManager competitionManager;
	private Scanner keyboard = new Scanner(System.in);
	
	public Program(){
		this.resultService = new ResultService();
		this.competitionManager = new CompetitionManager(this.resultService);
		this.competitionManager.addParticipant("Rosanna", "Sjöö");
		this.competitionManager.addParticipant("Albin", "Blent");
		this.competitionManager.addParticipant("Kalle", "Anka");
		this.competitionManager.addParticipant("aac", "bbb");
		this.competitionManager.addParticipant("aab", "bbb");
		this.competitionManager.addParticipant("aaa", "bbb");
		this.competitionManager.addParticipant("aaa", "abb");
		this.competitionManager.addParticipant("aaa", "aab");
		this.competitionManager.addParticipant("Kenny", "Starfighter");
		this.competitionManager.addEvent("Hoppa", 4);
		this.competitionManager.addEvent("Skutta", 4);
		this.competitionManager.addEvent("Hoppa", 4);
		this.competitionManager.addEvent("100 Meters", 2);
		this.competitionManager.addEvent("300 Meters", 2);
		this.resultService.addResult(100, "Hoppa", 3.4);
		this.resultService.addResult(103, "Hoppa", 3.4);
		this.resultService.addResult(104, "Hoppa", 3.4);
		this.resultService.addResult(105, "Hoppa", 3.4);
		this.resultService.addResult(106, "Hoppa", 3.4);
		this.resultService.addResult(107, "Hoppa", 2.4);
		this.resultService.addResult(108, "Hoppa", 5.4);
		this.resultService.addResult(101, "Hoppa", 3.4);
		this.resultService.addResult(101, "Hoppa", 1.4);
		this.resultService.addResult(101, "Hoppa", 2.4);
		this.resultService.addResult(101, "Hoppa", 4.4);
		this.resultService.addResult(101, "Hoppa", 5.4);
		this.resultService.addResult(101, "100 Meters", 13.4);
		this.resultService.addResult(101, "300 Meters", 33.4);
		this.resultService.addResult(101, "Skutta", 13.4);
		this.resultService.addResult(102, "Hoppa", 3.4);
		this.resultService.addResult(101, "Hoppa", 3.2);
		this.resultService.addResult(101, "Hoppa", 4.0);
	}

	public static void main(String[] args) {
		Program program = new Program();
		program.runCommandLoop();
	}
	
	public void menu(){
		System.out.println();
		System.out.println("1. Add event");
		System.out.println("2. Add participant");
		System.out.println("3. Remove participant");
		System.out.println("4. Add result");
		System.out.println("5. Participant result list");
		System.out.println("6. Exit");
		System.out.println("<EventName> For event results");
		System.out.println("<message text> For printing a message");
	}

	private String normalizeString(String str) {
	    str = str.toLowerCase();
		StringBuilder stringBuilder = new StringBuilder();
		for (String word : str.split(" ")) {
		    if (!word.isEmpty()) {
				String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
				stringBuilder.append(capitalizedWord + " ");
			}
		}
		return stringBuilder.toString().trim();
	}

	private String getUserInputString(String greeting, Boolean shouldNormalize, String errorMsg){
		System.out.print(greeting);
		String userInput = keyboard.nextLine().trim();
		if (shouldNormalize) {
			userInput = normalizeString(userInput);
		}
		if (userInput.isEmpty()){
			System.out.println(errorMsg);
			return getUserInputString(greeting, shouldNormalize, errorMsg);
		}
		return userInput;
	}

	/**
	 * För att förhindra att Scannern hoppar över nästa input
	 * @return
	 */
	private int getUserInputInt(){
		int i = keyboard.nextInt();
		keyboard.nextLine();
		return i;
	}

	private int getUserInputAttemptsAllowed(){
		int attemptsAllowed = getUserInputInt();
		if (attemptsAllowed <= 0){
			System.out.print("Error: too low, must allow at least one attempt: ");
			return getUserInputAttemptsAllowed();
		}
		return attemptsAllowed;
	}

	private double getUserInputResult(String greeting){
		System.out.print(greeting);
		double result = keyboard.nextDouble();
		if (result < 0.0){
			System.out.print("Error: must be greater than of equal to zero!");
			return getUserInputResult(greeting);
		}
		return result;
	}

	private String getEventNameFromUser(){
		String name = getUserInputString("Event name: ",
				true,
				"Error: name can't be empty!");
		if (competitionManager.checkIfEventExists(name)){
			System.out.println("Error: " + name + " has already been added");
			return getEventNameFromUser();
		}
		return name;
	}

	private void runCommandLoop(){
		boolean running = true;
		while (running) {
			menu();
			System.out.print("Command: ");
			String cmd = keyboard.nextLine();
			switch (cmd) {
			case "1":
			case "add event":
				String name = getEventNameFromUser();
				System.out.print("Attempts allowed: ");
				int attemptsAllowed = getUserInputAttemptsAllowed();
				competitionManager.addEvent(name, attemptsAllowed);
				System.out.println(name + " added");
				break;
			case "2":
			case "add participant":
				String forename = getUserInputString("First name: ",
						true,
						"Error: name can't be empty!");
				String surname = getUserInputString("Last name: ",
						true,
						"Error: name can't be empty!");
				int number = competitionManager.addParticipant(forename, surname);
				System.out.println(forename + " " + surname + " with number " + number + " added");
				break;
			case "3": //remove participant
			case "remove participant":
				System.out.println("Number");
				int startNumber = getUserInputInt();
				Participant participant = competitionManager.removeParticipant(startNumber);
				if (participant == null) {
					System.out.println("Error: no participant with number " + startNumber + " exists");
					break;
				}
				System.out.println(participant.getForename() + " " + participant.getSurname() +
					" with number " + startNumber + " removed");
				break;
			case "4": //add result
			case "add result":
				System.out.print("Number: ");
				int participantStartNumber = getUserInputInt();
				Participant participant1 = competitionManager.getParticipant(participantStartNumber);
				if (participant1 == null){
					System.out.println("Error: no participant with number " + participantStartNumber + " found!");
					break;
				}
				String eventName = getUserInputString("Event: ",
						true,
						"Error: event name can't be empty");
				Event event = competitionManager.getEvent(eventName);
				if (event == null) {
					System.out.println("Error: no event called \""+ eventName + "\" found!");
					break;
				}
				int amountOfAttemptsForParticipantOnEvent = resultService.getAmountOfAttemptsForParticipantOnEvent(participantStartNumber, eventName);
				if (amountOfAttemptsForParticipantOnEvent >= event.getAttemptsAllowed()) {
					System.out.println("Error: " + participant1.getForename() + " " + participant1.getSurname() +
						" has already made " + event.getAttemptsAllowed() + " attempts in " + event.getEventName());
					break;
				}
				String resultGreeting = "Results for " + participant1.getForename() + " " + participant1.getSurname() +
						" in " + event.getEventName() + ": ";
				double res = getUserInputResult(resultGreeting);
				resultService.addResult(participantStartNumber, eventName, res);
				System.out.println("Added result " + res + " for "+ participant1.getForename() + " " +
						participant1.getSurname() + " in event " + event.getEventName());
				break;
			case "5":
			case "participant":
				System.out.print("Number: ");
				int parStartNumber = getUserInputInt();
				competitionManager.printResultForParticipant(parStartNumber);
				break;
			case "6":
			case "exit":
					System.out.println("Programmet avslutas");
					running = false;
					break;
			default:
				String eventNameForResults = normalizeString(cmd);
				if (competitionManager.checkIfEventExists(eventNameForResults)) {
					// Skriv ut event results
					System.out.println("Results for " + eventNameForResults);
					competitionManager.printResultForEvent(eventNameForResults);
					break;
				}
				String[] messageArray = cmd.split(" ", 2);
				if (messageArray[0].equals("message")) {
					// Skriv ut meddelande
					System.out.println("Printing message");
					System.out.println(messageArray[1]);
				}
				System.out.println("Error: unknown command " + cmd);
				break;
			}
			keyboard = new Scanner(System.in);
		}
	}
}
