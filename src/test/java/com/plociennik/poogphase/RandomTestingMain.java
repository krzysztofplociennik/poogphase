package com.plociennik.poogphase;

import org.joda.time.DateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class RandomTestingMain {

    public static void main(String[] args) {
//        MessageManager manager = new MessageManager();
//        User mark = new User(), paula = new User(), cecil = new User();
//        mark.setFirstName("Mark");
//        paula.setFirstName("Paula");
//        cecil.setFirstName("Cecil");
//
//        ChatMessage chatMessage1 = new ChatMessage(1L, mark, paula, "Hey Paula!", LocalDateTime.of(
//                LocalDate.of(2020, 11, 24), LocalTime.of(16, 52)));
//        ChatMessage chatMessage2 = new ChatMessage(1L, paula, mark, "Oh, hey Mark! How are you?", LocalDateTime.of(
//                LocalDate.of(2020, 11, 24), LocalTime.of(16, 54)));
//        ChatMessage chatMessage3 = new ChatMessage(1L, mark, paula, "I'm fine, as always, how are you?", LocalDateTime.of(
//                LocalDate.of(2020, 11, 24), LocalTime.of(16, 56)));
//        ChatMessage chatMessage4 = new ChatMessage(1L, paula, mark, "Great, I'm great. Thanks!", LocalDateTime.of(
//                LocalDate.of(2020, 11, 24), LocalTime.of(16, 57)));
//
//        manager.sendMessage(mark, paula, chatMessage1);
//        manager.sendMessage(paula, mark, chatMessage2);
//        manager.sendMessage(mark, paula, chatMessage3);
//        manager.sendMessage(paula, mark, chatMessage4);
//
//        manager.showChatLog(paula, mark);
//        manager.showChatLog(mark, cecil);

//        long number1 = 1;
//        long number2 = 2;
//
//        System.out.println(number1 + 2 == number2);

//        String user1 = "mark";
//        String user2 = "paula";
//        String user3 = "peter";
//        String id = user1 + user2;
//
//        System.out.println(id.contains(user3));

//        User user = new User();
//        User user2 = new User();
//
//        System.out.println(user.hashCode());
//        System.out.println(user2.hashCode());

//        System.out.println((int)(Math.random() * 8) + 1);

//        System.out.println("Do you want to delete ALL data? Y or N");
//        String input = new Scanner(System.in).nextLine();
//        if (input.equals("Y")) {
////            for (Post instance : postRepository.findAll()) {
////                postRepository.deleteAll();
////            }
//            System.out.println("The repository is now empty. ");
//        } else {
//            System.out.println("Aborted.");
//        }

//        User user1 = new User();
//        user1.setUsername("asasd");
//        User user2 = new User();
//        user2.setUsername("asqqqqqq");
//
//        System.out.println(user1.hashCode() + "\n" + user2.hashCode());

//        List<String> list = new ArrayList<>();
//        list.add("dummy");
//        list.add("silly");
//        list.add("dummy");
//        list.add("silly");
//        list.add("dummy");
//        list.add("dummy");
//        list.add("dummy");
//        list.add("dummy");
//
//        String dummy = "dummy";
//
//        System.out.println("Size of the list: " + list.size());
//
////        list.remove("dummy");
//        list = list.stream().filter(s -> !s.equals("dummy")).collect(Collectors.toList());
//
//        System.out.println("Size of the list: " + list.size());
//        for (String s : list) {
//            System.out.println(s);
//        }

//        String user = "mark", user2 = "paula", signature = user + "&" + user2;
//
//        System.out.println(signature);
//
//        String[] split = signature.split("&");
//        for (String s : split) {
//            System.out.println(s);
//        }

//        Map<String, String> map = new HashMap<>();
//        System.out.println(map.size());
//        map.put("ex", "1");
//        System.out.println(map.size());

//        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.of(2020, 1, 9), LocalTime.of(14, 35));
//        LocalDateTime dateTime2 = LocalDateTime.now();
//        LocalDate date = dateTime2.toLocalDate();
//
//        System.out.println(dateTime1.compareTo(dateTime2));
//
//        Period between = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
//
//        System.out.println("Period: " + Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate()));
//
//        System.out.println(between.getYears());
//
//        System.out.println(date);
//
//        System.out.println(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH ));

        DateTime yodaDateTime = new DateTime(2020, 1, 20, 7, 36);

        System.out.println(DateTime.now());

        System.out.println(yodaDateTime);
    }
}
