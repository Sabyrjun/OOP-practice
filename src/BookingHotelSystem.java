import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.sql.*;

public class BookingHotelSystem {

    // sql keys
    private static final String DB_URL = "...";
    private static final String USER ="...";
    private static final String PASS = "";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


// новый лист ток в sql

    public void addRoom(Room room){
        String sql = "INSERT INTO rooms (number, type, price, avaliable) VALUES(?, ?, ?, ?)";
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, room.getNumber());
            stmt.setString(2, room.getType());
            stmt.setDouble(3, room.getPrice());
            stmt.setBoolean(4, room.isAvailable());

            stmt.executeUpdate();
            System.out.println("Room saved to Databese!");
        }
        catch (SQLException e){
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    public void addGuest(Guest guest) {
        String sql = "INSERT INTO rooms (id, full_name, phone,) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, guest.getId());
            stmt.setString(2, guest.getFullName());
            stmt.setDouble(3, guest.getPhone());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Error adding guest: " + e.getMessage());
        }
    }

    public List<Room> getRooms(){
       List<Room> rooms = new ArrayList<>();
       String sql = "SELECT * FROM rooms";

       try (Connection conn = getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql)){
           while (rs.next()){
               Room room = new Room(
                       rs.getInt("number"),
                       rs.getString("type"),
                       rs.getDouble("price"),
                       rs.getBoolean("available"));
               rooms.add(room);
           }
       }
       catch (SQLException E){
           System.out.println("Error reading rooms: " + e.getMessage());
       }
       return rooms;
    }

    public List<Booking> getBookings(){
        return new ArrayList<>(bookings);
    }

    // поиск
    public Room findRoomByNumber(int number){
        String sql = "SELECT * FROM rooms WHERE number = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new Room(
                  rs.getInt("number"),
                  rs.getString("type"),
                  rs.getDouble("price"),
                  rs.getBoolean("available")
                );}
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Guest findGuestById(int id){
        for (Guest j : guests){
            if(j.getId() == id) return j;
        }
        return null;
    }

    // сортировка
    public void sortRoomsByPrice(){
        rooms.sort(Comparator.comparingDouble(Room :: getPrice));
    }

    // фильтр
    public List<Room> filterAvailableByType(String type){
        List<Room> result = new ArrayList<>();
        for (Room i : rooms){
            if (i.isAvailable() && i.getType().equalsIgnoreCase(type)){
                result.add(i);
            }
        }
        return result;
    }

    public Booking createBooking(int Bid, int roomNumber, int guestId, int nights){
        Room room = findRoomByNumber(roomNumber);
        Guest guest = findGuestById(guestId);

        if (room == null || guest == null) return null;
        if (!room.isAvailable()) return null;

        Booking booking = new Booking(Bid, room, guest, nights);
        bookings.add(booking);
        room.setAvailable(false);
        return booking;
    }

    // классы

    // инкопсуляция
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
        public double getPrice() { return price; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available){this.available = available;}
        @Override
        public String toString() { return "Room{number=" + number + ", type='" + type + '\'' + ", price per night=" + price + ", avaliable=" + available + "}"; }

        // иквал + хэшкод
        @Override
        public boolean equals(Object i){
            if(this == i) return true;
            if (!(i instanceof Room)) return false;
            Room room = (Room) i;
            return number == room.number;
        }

        @Override
        public int hashCode(){
            return Objects.hash(number);
        }
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

    // иквал + хэшкод
    @Override
    public boolean equals(Object i){
        if(this == i) return true;
        if (!(i instanceof Person)) return false;
        Person person = (Person) i;
        return id == person.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}

    public static class Guest extends Person {
        public Guest(int id, String fullName, String phone) {
            super(id, fullName, phone);
        }

        @Override
        public String role() {
            return "Guest";
        } // полиморфизм, оно делает в наследник
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
    public double getTotalCost(){ return room.getPrice() * nights; }
    @Override
    public String toString(){ return "Booking{bookingId=" + bId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalCost() + '}'; }

    // иквал + хэшкод
    @Override
    public boolean equals(Object i){
        if(this == i) return true;
        if (!(i instanceof Booking)) return false;
        Booking booking = (Booking) i;
        return bId == booking.bId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(bId);
    }
    }
}