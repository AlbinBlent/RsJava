//Rosanna Sjöö, rosj4727, grupp 078
public class Result {

    private Event event;
    private Participant participant;
    private double score;

    public Result(Event event, Participant participant, double score){
        this.event = event;
        this.participant = participant;
        this.score = score;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}