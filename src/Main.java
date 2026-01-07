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
            System.out.println("Press 4 to find room by number");
            System.out.println("Press 5 to sort rooms by price");
            System.out.println("Press 6 to show available type of room");
            System.out.println("Press 0 to exit");

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

                    System.out.println("Added: " + room);
                    break;
                }
                case 2:{
                    System.out.print("Guest id: ");
                    int id = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Full name: ");
                    String fullName = scan.nextLine();

                    System.out.print("Phone: ");
                    String phone = scan.nextLine();

                    BookingHotelSystem.Guest guest = new BookingHotelSystem.Guest(id, fullName, phone);
                    system.addGuest(guest);

                    System.out.println("Added: " + guest);
                    break;
                }
                case 3:{
                    System.out.print("Booking id: ");
                    int Bid = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Room number: ");
                    int roomNumber = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Guest id: ");
                    int guestId = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Nights: ");
                    int nights = scan.nextInt();
                    scan.nextLine();
                    BookingHotelSystem.Booking booking = system.createBooking(Bid, roomNumber, guestId, nights);
                }
                case 4: {
                    System.out.print("Room number to search: ");
                    int number = scan.nextInt();
                    scan.nextLine();

                    BookingHotelSystem.Room room = system.findRoomByNumber(number);
                    System.out.println(room == null ? "Not found." : "Found: " + room);
                    break;
                }
                case 5:{
                    system.sortRoomsByPrice();
                    System.out.println("Rooms sorted by price: ");
                    for (BookingHotelSystem.Room i : system.getRooms()){
                        System.out.println(i);
                    }
                    break;
                }
                case 6:{
                    System.out.print("Type for filter: ");
                    String type = scan.nextLine();

                    System.out.println("Available rooms, type = " + type + ": ");
                    for (BookingHotelSystem.Room i : system.filterAvaliableByType(type)){
                        System.out.println(i);
                    }
                    break;
                }
                default:
                    System.out.println("Unknown option. May i get 80% pls? :3");
            }
        }
        scan.close();
    }
}
