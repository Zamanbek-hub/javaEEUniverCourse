package db.classes;

public class Alert {
    private boolean success;
    private String message;
    private boolean hidden;

    public Alert() {
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Alert(boolean success, String message, boolean hidden) {
        this.success = success;
        this.message = message;
        this.hidden = hidden;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", hidden=" + hidden +
                '}';
    }
}
