package controller;

import dao.ForgotPasswordDao;

public class ForgotPasswordController {

    private final ForgotPasswordDao fg = new ForgotPasswordDao();

    public boolean validateEmail(String email) {
        try {
            return fg.isEmailExist(email);
        } catch (Exception ex) {
            // Let the View handle the error message
            return false;
        }
    }

    public String getSecurityQuestion(String email) {
        try {
            return fg.getSecurityQuestion(email);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean checkSecurityAnswer(String email, String answer) {
        try {
            return fg.getAns(email, answer);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean resetPassword(String email, String newPassword) {
        try {
            fg.setNewPassword(email, newPassword);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
