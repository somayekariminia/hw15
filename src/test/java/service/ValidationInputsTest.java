package service;

import Exeption.ValidationException;
import Util.ValidationInputs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValidationInputsTest {
    ValidationInputs validationInputs = ValidationInputs.getInstance();


    public static Collection checkPassword() {
        return Arrays.asList(new Object[][]{
                {"SSoo66@@"},
                {"moM67!D!"}
        });
    }

    @ParameterizedTest
    @MethodSource(value = "checkPassword")
    void testCheckPasswordByParameterSize(String string) {
        String result = validationInputs.checkUserName(string);
        assertEquals(string, result);
    }

    public static List[] data() {
        return new List[]{
                Arrays.asList("SSoo66oo", "yourPassword is invalid"),
                Arrays.asList("mom67!D!D", "length your password more 8"),
                Arrays.asList("mom67Dhh", "yourPassword is invalid"),
                Arrays.asList("mommomDD!", "yourPassword is invalid"),
                Arrays.asList("momtgf@@@", "yourPassword is invalid"),
                Arrays.asList("mom67!!", "yourPassword is invalid"),
                Arrays.asList("moM67!D!*", "length your password more 8")
        };
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    void testCheckPasswordInvalidationByParameterSize(List<String> list) {
        String string1 = list.get(0);
        String string2 = list.get(1);
        Exception exception = assertThrows(ValidationException.class, () -> validationInputs.checkUserName(string1));
        assertTrue(string2.equals(exception.getMessage()));
    }
}
