package model;

public class ForgotPassword {

    private String email;
    private String securityQuestion;
    private String answer;
    private String newPassword;

    public ForgotPassword() {
    }

    public ForgotPassword(String email, String securityQuestion, String answer, String newPassword) {
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.answer = answer;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
