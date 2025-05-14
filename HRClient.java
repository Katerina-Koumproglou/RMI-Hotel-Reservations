import java.rmi.*;
import java.util.*;

public class HRClient {

    public static void main(String[] args) {

        //Αν ο χρήστης δεν έχει βάλει καμία παράμετρο στο command line
        if (args.length == 0){
            System.out.println("Παράμετροι:");
            System.out.println("java HRClient list <hostname>");
            System.out.println("java HRClient book <hostname> <type> <number> <name>");
            System.out.println("java HRClient guests <hostname>");
            System.out.println("java HRClient cancel <hostname> <type> <number> <name>");
            return;
        }

        try {
            String commandLine = args[0];
            String hostname;
            if (args.length > 1){
                hostname = args[1];
            }else{
                hostname = "localhost";
            }

            HRInterface hr = (HRInterface) Naming.lookup("rmi://" + hostname + "/HR");

            switch(commandLine){
                case "list":
                    List<String> prices = hr.roomsList();

                    for (int i = 0; i < prices.size(); i++){
                        String room = prices.get(i);
                        System.out.println(room);
                    }
                    break;

                case "book":
                    if (args.length < 5){
                        System.out.println("Λάθος αριθμός παραμέτρων! Η σωστή μορφή είναι:");
                        System.out.println("java HRClient book <hostname> <type> <number> <name>");
                        return;
                    }

                    String type = args[2];
                    int number = Integer.parseInt(args[3]);
                    String name = args[4];
                    System.out.println(hr.bookRoom(type, number, name));
                    break;

                case "guests":
                    List<String> guestList = hr.guestList();

                    for (int i = 0; i < guestList.size(); i++){
                        String guest = guestList.get(i);
                        System.out.println(guest);
                    }
                    break;

                case "cancel":
                    if (args.length < 5){
                        System.out.println("Λάθος αριθμός παραμέτρων! Η σωστή μορφή είναι:");
                        System.out.println("java HRClient cancel <hostname> <type> <number> <name>");
                        return;
                    }

                    // String type = args[2];
                    // int number = Integer.parseInt(args[3]);
                    // String name = args[4];
                    break;

                default:
                    System.out.println("Λάθος παράμετροι! Παρακαλώ προσπαθήστε ξανά:");
                    System.out.println("java HRClient list <hostname>:");
                    System.out.println("java HRClient book <hostname> <type> <number> <name>");
                    System.out.println("java HRClient guests <hostname>");
                    System.out.println("java HRClient cancel <hostname> <type> <number> <name>");
                    break;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
