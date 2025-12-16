public class Booking {
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
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number;
        }
        public String getType() {
            return type;
        }
        public double getPrice() {
            return price;
        }
        public boolean isAvaliable() {
            return avaliable;
        }
        public void setAvaliable(boolean avaliable) {
            this.avaliable = avaliable
        }
        public double calcCost(int nights) {
            return price * nights;
        }
    }
    @Override
    public String toString() {
        return "Room{number=" + number + ", type='" + type + '\'' + ", price per night=" + price + ", avaliable=" + avaliable + "}";
    }
}


class Guest{
    private int id;
    private String fullName;
    private String phone;

    public Guest(int id, String fullName, String phone){
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getFullName(){return id;}
    public void setFullName(String fullName){this.fullName = fullName;}

    public Srting getPhone(){return phone}
    public void setPhone(String phone){this.phone = phone}

    @Override
    public String toString(){
        return "Booking{" + "bookingId=" + bookingId + ", room=" + room + ", guest=" + guest + ", nights=" + nights + ", totalPrice=" + getTotalPrice() + '}';
    }


















}








































