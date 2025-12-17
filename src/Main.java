public class Main {
    public static void main (String[] inf) {

        BookingHotelSystem system = new BookingHotelSystem();

        int roomNumber = 101;
        String roomType = "Single";
        double price = 5000.5;
        boolean available = true;

        BookingHotelSystem.Room room = system.new Room(roomNumber, roomType, price, available);

        int guestId = 1;
        String name = "CJ";
        String phone = "+7 777 777 77 77";

        BookingHotelSystem.Guest guest = system.new Guest(guestId, name, phone);

    }
}