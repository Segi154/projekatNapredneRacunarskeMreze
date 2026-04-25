package rs.ac.bg.fon.projekat_nrt.exception;

public class SameTrainingShouldBeRecorded extends RuntimeException{
    public SameTrainingShouldBeRecorded() {
        super();
    }

    public SameTrainingShouldBeRecorded(String message) {
        super(message);
    }

    public SameTrainingShouldBeRecorded(String message, Throwable cause) {
        super(message, cause);
    }
}
