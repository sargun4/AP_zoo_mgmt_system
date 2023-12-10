public class Feedback {
    private String visitorName;
    private String feedbackText;

    public Feedback(String visitorName, String feedbackText) {
        this.visitorName = visitorName;
        this.feedbackText = feedbackText;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}


