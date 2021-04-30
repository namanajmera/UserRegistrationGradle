final class ERROR_MESSAGE {
    public static final String fname = "First name need to have at least 2 character with first letter capital";
    public static final String lname = "Last name need to have at least 2 character with first letter capital";
    public static final String email = "Enter a valid email id";
    public static final String mobile = "Enter a valid mobile number";
    public static final String password="Password has to be of 8 characters, with one capital,one number and one special character";

}

public class IllegalFieldValueException extends Throwable {
    IllegalFieldValueException(String msg) {
        super(msg);
    }
}