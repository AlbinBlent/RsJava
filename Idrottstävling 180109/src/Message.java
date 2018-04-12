//Rosanna Sjöö, rosj4727, grupp 078s

//lägg in blankrad före och efter. "Ramen ska ha en fast bredd på 60 tecken och minst en blankrad både före och efter"
public class Message {
	public String convertMessage(String message) {
		String upperFrame = "\n" + getRowOfCharacters('#', 60) + "\n";
		String lowerFrame = getRowOfCharacters('#', 60) + "\n";
		StringBuilder messageBody = new StringBuilder();
		
		messageBody.append("#                                                          #\n");
		
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
		messageBody.append("#                                                          #\n");
		

		return upperFrame + messageBody.toString().toUpperCase() + lowerFrame;
	}

	private String getRowOfCharacters(char c, int amount){
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < amount; i++) {
			row.append(c);
		}
		return row.toString();
	}
}
