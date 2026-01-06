import java.util.Scanner;
public class Main {
    public static void main(String[] inf) {

        BookingHotelSystem system = new BookingHotelSystem();
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("\n --- HH KZ ---");
            System.out.println("Press 1 to add new room");
            System.out.println("Press 2 to add guest");
            System.out.println("Press 3 to create a booking");

            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 0) break;

            switch (choice){
                case 1:{
                    System.out.print("Room number: ");
                    int number = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Room type (single/double/triple: ");
                    String type = scan.nextLine();

                    System.out.print("Price per night: ");
                    double price = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Available (true/false): ");
                    boolean available = scan.nextBoolean();
                    scan.nextLine();

                    BookingHotelSystem.Room room = new BookingHotelSystem.Room(number, type, price, available);
                    system.addRoom(room);
                }









            }






        }



    }
}