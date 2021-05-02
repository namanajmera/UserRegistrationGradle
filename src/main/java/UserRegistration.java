import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String mobile;
    private static String password;

    public static final Predicate<String> validateFName = name -> (
            Pattern.matches("[A-Z][a-z]{2,}", name)
    );

    public static final Predicate<String> validateLName = name -> (
            Pattern.matches("[A-Z][a-z]{2,}", name)

    );

    public static final Predicate<String> validateEmail = email -> (
            Pattern.matches("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", email)
    );

    public static final Predicate<String> validateMobile = number -> (
            Pattern.matches("[0-9]{2}+([ ])+([0-9]{10})", number)
    );

    public static final Predicate<String> validatePassword = password -> {
        if (password.length() < 8)
            return false;
        Matcher splChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password);
        int splCharCount = 0;
        while (splChar.find())
            splCharCount++;
        return Pattern.matches(".*[A-Z].*", password) &&
                Pattern.matches(".*[0-9].*", password) &&
                (splCharCount == 1);
    };

    public static String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IllegalFieldValueException {
        if (!validateFName.test(firstName))
            throw new IllegalFieldValueException(ERROR_MESSAGE.fname);
        UserRegistration.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalFieldValueException {
        if (!validateEmail.test(email))
            throw new IllegalFieldValueException(ERROR_MESSAGE.email);
        UserRegistration.email = email;
    }

    public static String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) throws IllegalFieldValueException {
        if (!validateMobile.test(mobile))
            throw new IllegalFieldValueException(ERROR_MESSAGE.mobile);
        UserRegistration.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalFieldValueException {
        if (!validatePassword.test(password))
            throw new IllegalFieldValueException(ERROR_MESSAGE.password);
        UserRegistration.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalFieldValueException {
        if (!validateLName.test(lastName))
            throw new IllegalFieldValueException(ERROR_MESSAGE.lname);
        UserRegistration.lastName = lastName;
    }

    public static void main(String[] args) {
        UserRegistration user = new UserRegistration();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter first name:");
            user.setFirstName(sc.nextLine());
            System.out.println("Enter last name:");

            user.setLastName(sc.nextLine());
            System.out.println("Enter Email id:");
            user.setEmail(sc.nextLine());
            System.out.println("Enter mobile number:");
            user.setMobile(sc.nextLine());
            System.out.println("Enter password:");
            user.setPassword(sc.nextLine());

        } catch (IllegalFieldValueException e) {
            e.printStackTrace();
        }
    }
}