import java.rmi.*;
import java.util.*;

public interface HRInterface extends Remote{
    
    List<String> roomsList() throws RemoteException;
    String bookRoom(String type, int number, String name) throws RemoteException;
    List<String> guestList() throws RemoteException;
    //String cancelRoom(String type, int number, String name) throws RemoteException;
}
