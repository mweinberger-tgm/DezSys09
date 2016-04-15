package at.mweinberger.tgm.DezSys09.model;

/**
 * Zustaendig fuer die Benachrichtigung von REST-Anfragen.
 */
public class Message {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    /**
     * Aufbau eines Message-Objekts.
     */
    public Message(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
