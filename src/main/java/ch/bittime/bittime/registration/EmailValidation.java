package ch.bittime.bittime.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

public class EmailValidation implements Predicate<String>{

    @Override
    public boolean test(String s){
        return true;
    }

}
