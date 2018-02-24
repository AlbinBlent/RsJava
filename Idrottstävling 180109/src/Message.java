//Rosanna Sjöö, rosj4727, grupp 078s
public class Message {
	public String convertMessage(String message) {
		String upperFrame = getRowOfCharacters('#', 60) + "\n";
		String lowerFrame = getRowOfCharacters('#', 60) + "\n";
		StringBuilder messageBody = new StringBuilder();
		int messageLength = message.length();
		int division = (messageLength/56);

		int index = 0;
		for (int i = 0; i < message.length()/56; i++) {
			messageBody.append("# ");
			messageBody.append(message.substring(i * 56, (i * 56) + 56));
			messageBody.append(" #");
			messageBody.append("\n");
			index++;
		}
		int remainder = messageLength % 56;
		messageBody.append("# ");
		messageBody.append(message.substring(index * 56, (index * 56) + remainder));
		messageBody.append(getRowOfCharacters(' ', (56 - remainder)));
		messageBody.append(" #");
		messageBody.append("\n");

		return upperFrame + messageBody.toString() + lowerFrame;
	}

	private String getRowOfCharacters(char c, int amount){
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < amount; i++) {
			row.append(c);
		}
		return row.toString();
	}
}
