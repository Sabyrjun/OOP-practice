import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;


public class BookingHotelSystem {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Guest> guests = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();


    public void addRoom(Room room){
        rooms.add(room);}

    public void addGuest(Guest guest){
        guests.add(guest);}

    public List<Room> getRooms(){
        return new ArrayList<>(rooms);
    }

    public List<Booking> getBookings(){
        return new ArrayList<>(bookings);
    }


    public Room findRoomByNumber(int number){
        for (Room i : rooms){
            if(i.getNumber() == number) return i;
        }
        return null;
    }

    public Guest findGuestById(int id){
        for (Guest j : guests){
            if(j.getId() == id) return j;
        }
        return null;
    }


    public void sortRoomsByPrice(){
        rooms.sort(Comparator.comparingDouble(Room::getprice));
    }


    public List<Room> filterAvaliableByType(String type){
        List<Room> result = new ArrayList<>();
        for (Room i : rooms){
            if (i.isAvaliable() && i.getType().equalsIgnoreCase(type)){
                result.add(i);
            }
        }
        return result;
    }


    public Booking createBooking(int Bid, int roomNumber, int guestId, int nights){
        Room room = findRoomByNumber(roomNumber);
        Guest guest = findGuestById(guestId);

        if (room == null || guest == null) return null;
        if (!room.isAvaliable()) return null;

        Booking booking = new Booking(Bid, room, guest, nights);
        bookings.add(booking);
        room.setAvailable(false);
        return booking;


    }








    public static class Room {
        private int number;
        private String type;
        private double price;
        private boolean available;
        public Room(int number, String type, double price, boolean available) {
            this.number = number;
            this.type = type;
            this.price = price;
            this.available = available;
        }
        public int getNumber() { return number; }
        public String getType() { return type; }
        public double getprice() { return price; }
        public boolean isAvaliable() { return available; }
        public void setAvailable(boolean available){this.available = available;}
        @Override
        public String toString() { return "Room{number=" + number + ", type='" + type + '\'' + ", price per night=" + price + ", avaliable=" + available + "}"; }
    }
public static abstract class Person {
    private int id;
    private String fullName;
    private String phone;

    public Person(int id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public abstract String role();

    @Override
    public String toString() {
        return role() + "{id=" + id + ", fullName='" + fullName + '\'' + ", phone='" + phone + '\'' + "}";
    }
}

    public static class Guest extends Person {
        public Guest(int id, String fullName, String phone) {
            super(id, fullName, phone);
        }
        @Override
        public String role() {
            return "Guest";
        }
    }


public static class Booking{
    private int bId;
    private Room room;
    private Person guest;
    private int nights;
   public Booking (int bId, Room room, Guest guest, int nights){
       this.bId = bId;
       this.room = room;
       this.guest = guest;
       this.nights = nights;
   }
    public double getTotalcost(){ return room.getprice() * nights; }
    @Override
    public String toString(){ return "Booking{bookingId=" + bId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalcost() + '}'; }
    }
}