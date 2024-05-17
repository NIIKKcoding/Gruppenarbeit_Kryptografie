package OneTimePad;

public class OTPModel {
    private String pad = ""; // Initialisieren mit leerem String
    private int padPosition = 0; // Position im Pad tracken

    public void setPad(String newPad) {
        pad = newPad;
        padPosition = 0; // Position zurücksetzen, wenn ein neues Pad geladen wird
    }

    public String encryptMessage(String message) {
        message = normalizeInput(message);
        validateMessage(message);
        return processMessage(message, true);
    }

    public String decryptMessage(String message) {
        message = normalizeInput(message);
        validateMessage(message);
        return processMessage(message, false);
    }

    private String normalizeInput(String input) {

        return input.replaceAll("ä", "ae")
                .replaceAll("ö", "oe")
                .replaceAll("ü", "ue");
    }

    private void validateMessage(String message) {

        if (!message.chars().allMatch(c -> c >= 0x20 && c <= 0x7E) || padPosition + message.length() > pad.length()) {
            throw new IllegalArgumentException("Invalid input or insufficient pad length.");
        }
    }

    private String processMessage(String message, boolean encrypt) {

        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int messageVal = message.charAt(i) - 0x20;
            int padVal = pad.charAt(padPosition + i) - 0x20;
            int resultVal = encrypt ? (messageVal + padVal) % 95 : (messageVal - padVal + 95) % 95;
            processed.append((char) (resultVal + 0x20));
        }
        padPosition += message.length();
        return processed.toString();
    }
}
