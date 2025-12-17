public class Main {
    public static void main(String[] inf) {

        BookingHotelSystem system = new BookingHotelSystem();

        int roomNumber = 101;
        String roomType = "Single";
        double price = 5000.5;
        boolean available = true;

        BookingHotelSystem.Room room1 = system.new Room(roomNumber, roomType, price, available);

        int guestId = 1;
        String name = "CJ";
        String phone = "+7 777 777 77 77";

        BookingHotelSystem.Guest guest1 = system.new Guest(guestId, name, phone);

        int bookingId = 1;
        int nights = 7;

        BookingHotelSystem.Booking booking1 = system.new Booking(bookingId, room1, guest1, nights);

        System.out.println("HH.KZ");
        System.out.println(room1);
        System.out.println(guest1);
        System.out.println(booking1);
    }
}