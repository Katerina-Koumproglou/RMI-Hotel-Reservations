import java.rmi.*;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.rmi.server.UnicastRemoteObject;

public class HRImpl extends UnicastRemoteObject implements HRInterface{
    
    //Δημιουργία χάρτη για το πλήθος των δωματίων ανά τύπο
    private Map<String, Integer> rooms;

    //Δημιουργία χάρτη για τις τιμές των δωματίων
    private Map<String, Integer> prices;

    //Δημιουργία χάρτη για τις κρατήσεις των δωματίων
    private Map<String, Map<String, Integer>> bookings;

    //Δημιουργία χάρτη για τις κρατήσεις δωματίων ανά πελάτη (Εμπεριέχονται οι τύποι και το αντίστοιχο πλήθος δωματίων)
    private Map<String, Integer> userBookings;

    public HRImpl() throws RemoteException{
        super();
        initialiseRooms();
        initialisePrices();

        bookings = new ConcurrentHashMap<>();
    }

    //Αρχικοποίηση του χάρτη rooms με τους τύπους των δωματίων και το αντίστοιχο πλήθος δωματίων
    private void initialiseRooms(){
        rooms = new ConcurrentHashMap<>();
        rooms.put("A", 40);
        rooms.put("B", 35);
        rooms.put("C", 25);
        rooms.put("D", 30);
        rooms.put("E", 20);
    }

    //Αρχικοποίηση των τιμών των δωματίων με χρήση ConcurrentHashMap
    private void initialisePrices(){
        prices = new ConcurrentHashMap<>();
        prices.put("A", 75);
        prices.put("B", 110);
        prices.put("C", 120);
        prices.put("D", 150);
        prices.put("E", 200);
    }

    //java HRClient list <hostname>
    @Override
    public synchronized List<String> roomsList() throws RemoteException{
        List<String> result = new ArrayList<>();
        for (String key : rooms.keySet()){
            result.add(rooms.get(key) + " δωμάτια τύπου " + key + " - τιμή: " + prices.get(key) + " Ευρώ τη βραδιά");
        }

        return result;
    }

    //java HRClient book <hostname> <type> <number> <name>
    @Override
    public synchronized String bookRoom(String type, int number, String name) throws RemoteException{
        
        if (!prices.containsKey(type)){
            return "Ο τύπος δωματίου " + type + " δεν υπάρχει";
        }

        if (rooms.get(type) - number < 0){
            return "Αποτυχία κράτησης, δεν υπάρχουν διαθέσιμα δωμάτια για τον τύπο " + type;
        }

        int cost = number * prices.get(type);
        rooms.put(type, rooms.get(type) - number);

        if (!bookings.containsKey(name)){
            bookings.put(name, new ConcurrentHashMap<>());
        }

        userBookings = bookings.get(name);
        userBookings.put(type, userBookings.getOrDefault(type, 0) + number);

        if (number == 1){
            return "Επιτυχής κράτηση για 1 δωμάτιο τύπου " + type + " - Συνολικό κόστος: " +  cost + " Ευρώ τη βραδιά στο όνομα " + name;
        } else {
            return "Επιτυχής κράτηση για " + number + " δωμάτια τύπου " + type + " - Συνολικό κόστος: " +  cost + " Ευρώ τη βραδιά στο όνομα " + name;
        }
    }


    //java HRClient guests <hostname>
    @Override
    public synchronized List<String> guestList() throws RemoteException{
        List<String> result = new ArrayList<>();

        for (String guest : bookings.keySet()){
            userBookings = bookings.get(guest);

            StringBuilder sb = new StringBuilder();
            sb.append("Όνομα πελάτη: ").append(guest).append("\n");

            for (String type : userBookings.keySet()){
                int count = userBookings.get(type);
                int totalCost = count * prices.get(type);

                sb.append(count).append(" δωμάτια τύπου ").append(type).append(" - Συνολικό κόστος: ").append(totalCost).append(" Ευρώ τη βραδιά\n");
            }

            result.add(sb + "");
        }

        return result;
    }

    // //java HRClient cancel <hostname> <type> <number> <name>
    // @Override
    // public synchronized String cancelRoom(String type, int number, String name) throws RemoteException{
    //     if (!bookings.containsKey(name) || !bookings.get(name).containsKey(type)){
    //         return "Αποτυχία ακύρωσης, δεν υπάρχουν κρατήσεις για ακύρωση του τύπου δωματίου" + type + " στο όνομα " + name;
    //     }

        
    // }
}
