package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @DisplayName("Valid first name")
    @ParameterizedTest
    @ValueSource(strings = {
            "John",             // Valid (single part)
            "O'Neill",          // Valid (apostrophe between parts)
            "van-de",           // Valid (hyphen between parts), // Valid (multiple parts)
            "O'Ne",             // Valid (apostrophe and letters)
            "Ava"               // Valid (minimum 2 characters)
    })
    void validFirstName(String name) {
        assertTrue(Student.validateFirstName(name));
    }

    @DisplayName("Invalid first name")
    @ParameterizedTest
    @ValueSource(strings = {
            "A",                // Invalid (only 1 character)
            "O'Ne--ill",        // Invalid (adjacent hyphens)
            "O' Neill",         // Invalid (space before apostrophe)
            "-van",             // Invalid (starts with hyphen)
            "van-",             // Invalid (ends with hyphen)
            "O'Neill ",         // Invalid (ends with space)
            " "                 // Invalid (only space)
    })
    void invalidFirstName(String name) {
        assertFalse(Student.validateFirstName(name));
    }

    @DisplayName("Test valid last name")
    @ParameterizedTest
    @ValueSource(strings = {
            "Jemison Van de Graaff", // Valid (multiple parts)
            "John",             // Valid (single part)
            "O'Neill",          // Valid (apostrophe between parts)
            "van-de",           // Valid (hyphen between parts), // Valid (multiple parts)
            "O'Ne",             // Valid (apostrophe and letters)
            "Ava"               // Valid (minimum 2 characters)
    })
    void validLastName(String name) {
        assertTrue(Student.validateLastName(name));
    }

    @DisplayName("Test invalid last name")
    @ParameterizedTest
    @ValueSource(strings = {
            "A",                // Invalid (only 1 character)
            "O'Ne--ill",        // Invalid (adjacent hyphens)
            "O' Neill",         // Invalid (space before apostrophe)
            "-van",             // Invalid (starts with hyphen)
            "van-",             // Invalid (ends with hyphen)
            "O'Neill ",         // Invalid (ends with space)
            " "                 // Invalid (only space)
    })
    void invalidLastName(String name) {
        assertFalse(Student.validateLastName(name));
    }

    @DisplayName("Test valid emails")
    @ParameterizedTest
    @ValueSource(strings = {
            "user@example.com",
            "first.last@example.co.uk",
            "user+name@example.com",
            "simple@domain.com",
            "test.email@sub.domain.com",
            "username@domain.co",
            "valid.email@domain.travel",
            "user.name123@domain123.com",
            "user@sub.domain.org",
            "example@domain.travel",
            "jane.doe@example.com",
            "alice.smith@company.co.uk"

    })
    void testValidEmail(String email) {
        assertTrue(Student.validateEmail(email));
    }

    @DisplayName("Test invalid emails")
    @ParameterizedTest
    @CsvSource({
            "user@example.com, true",
            "first.last@example.co.uk, true",
            "user+name@example.com, true",
            "simple@domain.com, true",
            "test.email@sub.domain.com, true",
            "username@domain.co, true",
            "valid.email@domain.travel, true",
            "user.name123@domain123.com, true",
            "user@sub.domain.org, true",
            "example@domain.travel, true",
            "jane.doe@example.com, true",
            "alice.smith@company.co.uk, true",
            "125367at@zzz90.z9, true",
            "user@domain.123, true",
            "username@domain..com, true",
            "user@domain.c, true",
            "user@.domain.com, true",
            "user@domain.com., true",
            "user@domain#example.com, true",
            "plainaddress, false",
            "@missingusername.com, false",
            "username@, false",
            "username@domain, false",
            "user@domain,com, false",
            "user@sub@domain.com, false",
            "user@domain@com, false",
            "user@ex@domain.com, false",
    })
    void testEmailValidation(String email, boolean expectedValidity) {
        assertEquals(expectedValidity, Student.validateEmail(email));
    }
}