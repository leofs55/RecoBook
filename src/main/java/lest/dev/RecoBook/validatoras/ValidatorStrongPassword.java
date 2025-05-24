package lest.dev.RecoBook.validatoras;

import lest.dev.RecoBook.exception.WeakPasswordException;
import org.springframework.stereotype.Component;

@Component
public class ValidatorStrongPassword {

    public boolean validate(String password) {
        String validators =  "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        if (password != null && password.matches(validators)){
            return true;
        }
        return false;
    }

}
