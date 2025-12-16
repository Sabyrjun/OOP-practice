public class BookingHotelSystem {
    class Room {
        private int number;
        private String type;
        private double price;
        private boolean avaliable;

        public Room(int number, String type, double price, boolean avaliable) {
            this.number = number;
            this.type = type;
            this.price = price;
            this.avaliable = avaliable;
        }
        public int getNumber() {return number;}
        public String getType() {return type;}
        public double getprice(){return price;}
        public boolean isAvaliable() {return avaliable;}
    }
    @Override
    public String toString() {
        return "Room{number=" + number + ", type='" + type + '\'' + ", price per night=" + price + ", avaliable=" + avaliable + "}";
    }
}


class Guest {
    private int id;
    private String fullName;
    private String phone;

    public Guest(int id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    public int getId(){return id;}
    public String getFullName(){return id;}
    public Srting getPhone(){return phone}


    @Override
    public String toString(){
        return "Booking{" + "bookingId=" + bookingId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalPrice() + '}';
    }
}

class BookingHotelSystem{
    private int bId;
    private Room room;
    private Guest guest;
    private int nights;

    public Booking(int bId, Room room, Guest guest, int nights) {
        this.bId = bId;
        this.room = room;
        this.guest = guest;
        this.nights = nights;
        }
        public double getTotalcost() {
            return Room.price() * nights;
        }
        @Override
        public String toString(){
            return "Booking{" + "bookingId=" + bookingId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalPrice() + '}';
        }





    }




















}








































