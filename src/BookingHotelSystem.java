import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;

public class BookingHotelSystem {


    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JAVA";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms (number, type, price, available) VALUES(?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getNumber());
            stmt.setString(2, room.getType());
            stmt.setDouble(3, room.getPrice());
            stmt.setBoolean(4, room.isAvailable());

            stmt.executeUpdate();
            System.out.println("Room saved to Database!");
        }
        catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }


    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guests (id, fullName, phone) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guest.getId());
            stmt.setString(2, guest.getFullName());
            stmt.setString(3, guest.getPhone());

            stmt.executeUpdate();
            System.out.println("Guest saved!");
        }
        catch (SQLException e) {
            System.out.println("Error adding guest: " + e.getMessage());
        }
    }


    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                );
                rooms.add(room);
            }
        }
        catch (SQLException e) {
            System.out.println("Error reading rooms: " + e.getMessage());
        }
        return rooms;
    }



    public Room findRoomByNumber(int number) {
        String sql = "SELECT * FROM rooms WHERE number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getInt("number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Guest findGuestById(int id) {
        String sql = "SELECT * FROM guests WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Guest(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getString("phone")
                );
            }
        }
        catch (SQLException e) {
            System.out.println("Error finding guest: " + e.getMessage());
        }
        return null;
    }

    //сортировка
    public List<Room> sortRoomsByPrice() {
        List<Room> list = getRooms();
        list.sort(Comparator.comparingDouble(Room::getPrice));
        return list;
    }

    // фильтрп
    public List<Room> filterAvailableByType(String type) {
        List<Room> result = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE type = ? AND available = true";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new Room(
                        rs.getInt("number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateRoomAvailability(int number, boolean available) {
        String sql = "UPDATE rooms SET available = ? WHERE number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, available);
            stmt.setInt(2, number);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
    }

    // бронь
    public Booking createBooking(int Bid, int roomNumber, int guestId, int nights) {
        Room room = findRoomByNumber(roomNumber);
        Guest guest = findGuestById(guestId);

        if (room == null || guest == null) return null;
        if (!room.isAvailable()) return null;

        try (Connection conn = getConnection()) {
            updateRoomAvailability(roomNumber, false);

            String sql = "INSERT INTO bookings (bId, roomNumber, guestId, nights) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Bid);
                stmt.setInt(2, roomNumber);
                stmt.setInt(3, guestId);
                stmt.setInt(4, nights);

                stmt.executeUpdate();
                System.out.println("Booking saved with bId: " + Bid);
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        room.setAvailable(false);
        return new Booking(Bid, room, guest, nights);
    }













































































        //клаасы
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
        public String toString() { return "Room{number=" + number + ", type='" + type + '\'' + ", price per night=" + price + ", available=" + available + "}"; }

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
        public double getTotalCost(){ return room.getPrice() * nights; }
        @Override
        public String toString(){ return "Booking{bookingId=" + bId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalCost() + '}'; }

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