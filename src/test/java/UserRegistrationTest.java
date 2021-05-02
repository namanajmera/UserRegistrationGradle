import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
//import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {
    public UserRegistration user;

    @Before
    public void initializeTest() {
        user = new UserRegistration();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void checkNameTest() {
        Assert.assertTrue(UserRegistration.validateFName.test("Debojyoti"));

        Assert.assertFalse(UserRegistration.validateFName.test("mi"));
        Assert.assertFalse(UserRegistration.validateFName.test("D"));
        Assert.assertFalse(UserRegistration.validateFName.test("debojyoti"));
    }

    @Test
    public void emailTestRunner() {
        Result result = JUnitCore.runClasses(EmailValidationTest.class);

        for (Failure failure : result.getFailures())
            System.out.println(failure.toString());
        System.out.println(result.wasSuccessful());
    }

    @Test
    public void mobileTestValid() {
        Assert.assertTrue(UserRegistration.validateMobile.test("91 9874311017"));
    }

    @Test
    public void mobileTestInvalid() {
        Assert.assertFalse(UserRegistration.validateMobile.test("91 98 74311017"));
        Assert.assertFalse(UserRegistration.validateMobile.test("919 874311017"));
        Assert.assertFalse(UserRegistration.validateMobile.test("A91 9874311017"));
        Assert.assertFalse(UserRegistration.validateMobile.test("91b9874311017"));
    }

    @Test
    public void passwordTestValid() {
        Assert.assertTrue(UserRegistration.validatePassword.test("iMhQhPE8#"));
    }

    @Test
    public void passwordTestInvalid1() {
        Assert.assertFalse(UserRegistration.validatePassword.test("@iMhQhPE8#"));//spl. chracters exactly once
    }

    @Test
    public void passwordTestInvalid2() {
        Assert.assertFalse(UserRegistration.validatePassword.test("ihPE8#"));
    }

    @Test
    public void passwordTestInvalid3() {
        Assert.assertFalse(UserRegistration.validatePassword.test("abcdeghyierf&"));
    }

    @Test
    public void firstNameExceptionTest() throws IllegalFieldValueException {
        exception.expect(IllegalFieldValueException.class);
        exception.expectMessage(ERROR_MESSAGE.fname);
        user.setFirstName("D");
    }

    @Test
    public void lastNameExceptionTest() throws IllegalFieldValueException {
        exception.expect(IllegalFieldValueException.class);
        exception.expectMessage(ERROR_MESSAGE.lname);
        user.setLastName("m");
    }

    @Test
    public void emailExceptionTest() throws IllegalFieldValueException {
        exception.expect(IllegalFieldValueException.class);
        exception.expectMessage(ERROR_MESSAGE.email);
        user.setEmail("dmukh.work@.");
    }

    @Test
    public void mobileExceptionTest() throws IllegalFieldValueException {
        exception.expect(IllegalFieldValueException.class);
        exception.expectMessage(ERROR_MESSAGE.mobile);
        user.setMobile("5456678");
    }

    @Test
    public void passwordExceptionTest() throws IllegalFieldValueException {
        exception.expect(IllegalFieldValueException.class);
        exception.expectMessage(ERROR_MESSAGE.password);
        user.setPassword("abcdefghijklmn");
    }
}