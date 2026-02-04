package com.example.hotelapi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final BookingHotelSystem system = new BookingHotelSystem();

    // 1. Добавить комнату (Case 1)
    @PostMapping("/rooms")
    public String addRoom(@RequestParam int number, @RequestParam String type, @RequestParam double price, @RequestParam boolean available) {
        system.addRoom(new BookingHotelSystem.Room(number, type, price, available));
        return "Room added!";
    }

    // 2. Добавить гостя (Case 2)
    @PostMapping("/guests")
    public String addGuest(@RequestParam int id, @RequestParam String name, @RequestParam String phone, @RequestParam String city) {
        system.addGuest(new BookingHotelSystem.Guest(id, name, phone, city));
        return "Guest added!";
    }

    // 3. Создать бронь (Case 3)
    @PostMapping("/bookings")
    public String createBooking(@RequestParam int bId, @RequestParam int roomNum, @RequestParam int guestId, @RequestParam int nights) {
        var b = system.createBooking(bId, roomNum, guestId, nights);
        return (b != null) ? "Booking created: " + b : "Booking failed!";
    }

    // 4. Найти по номеру (Case 4)
    @GetMapping("/rooms/{number}")
    public BookingHotelSystem.Room findRoom(@PathVariable int number) {
        return system.findRoomByNumber(number);
    }

    // 5. Сортировка по цене (Case 5)
    @GetMapping("/rooms/sorted")
    public List<BookingHotelSystem.Room> getSorted() {
        return system.sortRoomsByPrice();
    }

    // 6. Фильтр по типу (Case 6)
    @GetMapping("/rooms/filter")
    public List<BookingHotelSystem.Room> filter(@RequestParam String type) {
        return system.filterAvailableByType(type);
    }

    // 7. Удалить комнату (Case 7)
    @DeleteMapping("/rooms/{number}")
    public String deleteRoom(@PathVariable int number) {
        system.deleteRoom(number);
        return "Deleted.";
    }

    // 8. Изменить цену (Case 8)
    @PutMapping("/rooms/{number}/price")
    public String updatePrice(@PathVariable int number, @RequestParam double price) {
        system.updateRoomPrice(number, price);
        return "Price updated.";
    }

    // 9. Изменить статус (Case 9)
    @PutMapping("/rooms/{number}/status")
    public String updateStatus(@PathVariable int number, @RequestParam boolean available) {
        system.updateRoomAvailability(number, available);
        return "Status updated.";
    }

    // 10. Изменить телефон (Case 10)
    @PutMapping("/guests/{id}/phone")
    public String updatePhone(@PathVariable int id, @RequestParam String phone) {
        system.updateGuestPhone(id, phone);
        return "Phone updated.";
    }

    // Доп: Все комнаты
    @GetMapping("/rooms")
    public List<BookingHotelSystem.Room> getAll() {
        return system.getRooms();
    }
}