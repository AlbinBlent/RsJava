import java.util.ArrayList;

public class ResultService {

    private ArrayList<Result> results;

    public ResultService(){
        this.results = new ArrayList<>();
    }

    /*
         Jag antog att removeParticipant också borde ta bort alla resultat som den deltagaren har??
     */
    public void removeParticipantFromResults(int participantStartNumber){
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

    public void addResult(int participantStartNubmber, String eventName, double res){
        Result result = new Result(participantStartNubmber, eventName, res);
        results.add(result);
    }

    public ArrayList<Result> getResultForParticipant(int participantStartNubmber){
        ArrayList<Result> resultsForParticipant = new ArrayList<>();
        for (Result result : results){
            if (result.getParticipantStartNubmber() == participantStartNubmber){
                resultsForParticipant.add(result);
            }
        }
        return resultsForParticipant;
    }

    public ArrayList<Result> getResultForEvent(String eventName){
        ArrayList<Result> resultsFromEvent = new ArrayList<>();
        for (Result result : results){
            if (result.getEventName().equals(eventName)){
                resultsFromEvent.add(result);
            }
        }
        return resultsFromEvent;
    }

    public int getAmountOfAttemptsForParticipantOnEvent(int participantStartNumber, String eventName) {
        int amountOfAttempts = 0;
        for (Result result : results) {
            if (result.getParticipantStartNubmber() == participantStartNumber && result.getEventName().equals(eventName)){
                amountOfAttempts++;
            }
        }
        return amountOfAttempts;
    }

}