package com.spiderweb.customer.utils;

import com.spiderweb.customer.exception.InvalidEmailException;
import com.spiderweb.customer.exception.InvalidNameException;
import com.spiderweb.customer.model.Customer;
import com.spiderweb.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerUtils {

    private final CustomerRepository customerRepository;

    public static Boolean isNotNull(Object object){
        return object != null;
    }

    public static void validateFields(Customer customer){
        if (isNameBlank(customer.getFirstName())) {
            throw new InvalidNameException("The First Name is not valid");
        }
        if (isNameBlank(customer.getLastName())) {
            throw new InvalidNameException("The Last Name is not valid");
        }
        if (!validateEmail(customer.getEmail())){
            throw new InvalidEmailException("Please Enter a valid Exception");
        }
    }

    private static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        // Check if '@' and '.' are present
        if (atIndex <= 0 || dotIndex <= 0 || dotIndex <= atIndex || dotIndex == email.length() - 1) {
            return false;
        }

        // Check if there are no consecutive dots
        for (int i = 0; i < email.length() - 1; i++) {
            if (email.charAt(i) == '.' && email.charAt(i + 1) == '.') {
                return false;
            }
        }

        return true;
    }

    private static boolean isNameBlank(String name) {
        return name == null || name.isBlank();
    }


}
