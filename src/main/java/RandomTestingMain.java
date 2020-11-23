import com.plociennik.poogphase.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class RandomTestingMain {
    public static void main(String[] args) {

        User paula = new User();
        paula.setDateOfBirth(LocalDate.of(2010, 1, 12));


        System.out.println(paula.getAge());
    }
}
