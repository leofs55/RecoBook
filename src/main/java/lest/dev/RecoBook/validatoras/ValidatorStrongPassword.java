package lest.dev.RecoBook.validatoras;

import org.springframework.stereotype.Component;

@Component
public class ValidatorStrongPassword {

    public boolean validate(String password) {
        String validators =  "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        if (password != null && password.matches(validators)){
            return true;
        }
        return false;
//        throw new WeakPasswordException("Senha fraca. Ela deve conter ao menos 8 caracteres, uma letra maiúscula, um número e um caractere especial.");
    }

}
