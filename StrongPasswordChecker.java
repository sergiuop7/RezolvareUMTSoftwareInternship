import java.util.Scanner;

public class StrongPasswordChecker {

    // Creating a method that determines the minimum changes that need to be made
    // to the password which is passed as an argument of type String
    public static int minimumPasswordChanges(String s) {
        int missingTypes = 0;  // The number of character types missing from the password (Lowercase, Uppercase, Digit)
        boolean hasLowercase = false; // Boolean variable which tracks if a lowercase letter is present in the password
        boolean hasUppercase = false; // Boolean variable which tracks if an uppercase letter is present in the password
        boolean hasDigit = false; // Boolean variable which tracks if a digit is present in the password
        int repeatingChars = 0; // Integer representing the number of repeating characters that exist in the password
        char prevChar = '\0'; // A character which contains the previous char of the current character (see below)
        int changes = 0; // The final number of changes that are to be made to the password in order to obtain a strong one

        // Check the length of the password
        if (s.length() < 6) {
            /* If the password has less than 6 characters, we need to add 6 - the length of the password characters
            in order to meet the required condition, so we are making (6 - (length of password)) changes*/
            changes += 6 - s.length();
        } else if (s.length() > 20) {
            /* If the password has more than 20 characters, we need to remove the length of the password - 20 characters
            in order to meet the required condition, so we are making ((length of password) - 20) changes*/
            changes += s.length() - 20;
        }

        // Check for missing types of characters
        for (char c : s.toCharArray()) { // Going through the array taking each character
            if (Character.isLowerCase(c)) {
                hasLowercase = true; // If the current character is a lowercase one, the lowercase existence condition is met
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true; // If the current character is an uppercase one, the uppercase existence condition is met
            } else if (Character.isDigit(c)) {
                hasDigit = true; // If the current character is a digit, the digit existence condition is met
            }
        }
        if (!hasLowercase) {  // If the boolean variable is still false, it means that the password has no lowercase letters
            missingTypes++;  // so we are increasing the variable which tells the missing parts of the password
        }
        if (!hasUppercase) {  // If the boolean variable is still false, it means that the password has no uppercase letters
            missingTypes++;   // so we are increasing the variable which tells the missing parts of the password
        }
        if (!hasDigit) {      // If the boolean variable is still false, it means that the password has no digits
            missingTypes++;   // so we are increasing the variable which tells the missing parts of the password
        }
        // Increasing the value of 'changes' variable with the value of the missing types of characters from the password
        changes += missingTypes;

        // Check for repeating characters
        for (char c : s.toCharArray()) { // Again we are traversing the entire string character by character
            if (c == prevChar) {    // If the current character is the same as the previous one
                repeatingChars++;   // We are increasing the number of repetitive characters in a row
                if (repeatingChars % 3 == 0) {  // If there are 3 characters which are repeating
                    changes++;  // We increment the number of changes needed to be made
                }
            } else {
                repeatingChars = 1;  // If the current character is different from the previous one,
            }                       // the repeatingChars variable is equal to 1, from the current character
            prevChar = c; // The previous character becomes the actual character at the end of the loop
        }

        // The function returns the number of changes required to obtain a strong password
        return changes;
    }


    public static void main(String[] args) {

        // Taking the string password as an input read from the keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        scanner.close();

        /* Initializing and integer variable as the outputted result of the method that returns the
        minimum number of changes that should be made in order to obtain a strong password*/
        int changes = minimumPasswordChanges(password);

        /* If the variable 'changes' is equal to 0 after the call of the method above, it means
        there are no modifications needed to be made for the chosen password as it is already a strong password*/
        if (changes == 0) {
            System.out.println("The password is strong.");  //Printing the appropriate message
        }
        /* In the other case, when 'changes' has a value different from zero, it means that we need to make
        a number of modifications to the password which is equal to that value of the changes variable*/
        else {
            System.out.println("The password needs " + changes + " changes to become strong."); //Printing the appropriate message
        }
    }

}
