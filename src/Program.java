import java.util.Scanner;


public class Program {
	
	private CompetitionManager competitionManager;
	private Scanner keyboard = new Scanner(System.in);
	
	public Program(){
		this.competitionManager = new CompetitionManager();
		competitionManager.addParticipant("Rosanna", "Sjöö");
		competitionManager.addParticipant("Albin", "Blent");
		competitionManager.addParticipant("Kalle", "Anka");
		competitionManager.addParticipant("aac", "bbb");
		competitionManager.addParticipant("aab", "bbb");
		competitionManager.addParticipant("aaa", "bbb");
		competitionManager.addParticipant("aaa", "abb");
		competitionManager.addParticipant("aaa", "aab");
		competitionManager.addEvent("Hoppa", 4);
		competitionManager.addEvent("Hoppa", 4);
		competitionManager.addResult(100, "Hoppa", 3.4);
		competitionManager.addResult(103, "Hoppa", 3.4);
		competitionManager.addResult(104, "Hoppa", 3.4);
		competitionManager.addResult(105, "Hoppa", 3.4);
		competitionManager.addResult(106, "Hoppa", 3.4);
		competitionManager.addResult(107, "Hoppa", 3.4);
		competitionManager.addResult(101, "Hoppa", 3.4);
		competitionManager.addResult(102, "Hoppa", 3.4);
		competitionManager.addResult(101, "Hoppa", 3.2);
		competitionManager.addResult(101, "Hoppa", 4.0);
	}

	public static void main(String[] args) {
		Program program = new Program();
		program.runCommandLoop();
	}
	
	public void menu(){
		System.out.println("---------------------------");
		System.out.println("1. Add event");
		System.out.println("2. Add participant");
		System.out.println("3. Remove participant");
		System.out.println("4. Add result");
		System.out.println("5. Participant result list");
		System.out.println("6. Event result list");
		System.out.println("7. Message");
		System.out.println("8. Exit");
	}
	
	private void runCommandLoop(){
		boolean running = true;
		while (running) {
			menu();
			System.out.println("Command: ");
			String cmd = keyboard.nextLine().toLowerCase();
			switch (cmd) {
			case "1":
				System.out.print("Name:");
				String name = keyboard.nextLine();
				System.out.print("Attempts");
				int attemptsAllowed = keyboard.nextInt();
				competitionManager.addEvent(name, attemptsAllowed);
				break;
			case "2":
				System.out.print("Forename:");
				String forename = keyboard.nextLine();
				System.out.print("Surename");
				String surname = keyboard.nextLine();
				competitionManager.addParticipant(forename, surname);
				break;
			case "3": //remove participant
				System.out.println("Startnumber");
				int startNumber = keyboard.nextInt();
				competitionManager.removeParticipant(startNumber);
				break;
			case "4": //add result
				System.out.println("Participants start number");
				int participantStartNumber = keyboard.nextInt();
				System.out.println("Event name");
				String eventName = keyboard.nextLine();
				System.out.println("Result");
				int res = keyboard.nextInt();
				competitionManager.addResult(participantStartNumber, eventName, res);
				break;
			case "5":
				System.out.println("Participants start number");
				int parStartNumber = keyboard.nextInt();
				competitionManager.printResultForParticipant(parStartNumber);
				break;
			case "6":
				System.out.println("Event name:");
				String eventName2 = keyboard.nextLine();
				competitionManager.printResultForEvent(eventName2);
				break;
			case "7": //Message
				break;
			case "8": //Exit
				System.out.println("Programmet avslutas");
				running = false;
				keyboard.close();
				System.exit(0);
				break;
			/*
			case "Add event":
				addEvent();
				break;
			case "Add participant":
				addParticipant();
				break;
			case "Remove participant":
				removeParticipant();
				break;
			case "Add result":
				break;
			case "Result list for participant":
				teamManager.printAllTeams();
				break;
			case "Result list for event":
				eventManager.printEvents();
				break;
			case "Message":
				break;
			case "Exit": //funkr ej??
				System.out.println("Programmet avslutas");
				running = false;
				keyboard.close();
				System.exit(0);
				break;
				*/
			}
			keyboard = new Scanner(System.in);
		}
	}
}
