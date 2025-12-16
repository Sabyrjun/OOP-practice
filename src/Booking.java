public class Booking {
    class Room{
        private int number;
        private String type;
        private double price;
        private boolean avaliable;

        public Room(int number, String type, double price, boolean avaliable){
            this.number = number;
            this.type = type;
            this.price = price;
            this.avaliable = avaliable;
        }
        public int getNumber() {return number;}
        public void setNumber(int number){this.number;}

        public String getType(){return type;}
        public double getPrice() {return price;}

        public boolean isAvaliable() {return avaliable;}
        public void setAvaliable(boolean avaliable){this.avaliable = avaliable}
    }





    }



}
