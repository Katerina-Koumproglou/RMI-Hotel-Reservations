import java.rmi.*;

public class HRServer {
    public HRServer(){
        try {
            HRImpl hr = new HRImpl();
            Naming.rebind("rmi://localhost/HR", hr);
            System.out.println("HRServer is running...");
        } catch (Exception e){
            System.out.println("HRServer failed: " + e);
        }
    }

    public static void main(String[] args){
        new HRServer();
    }
}
