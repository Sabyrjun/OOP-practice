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
    private Guest guest;
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