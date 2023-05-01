package uz.shdsh.files_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.shdsh.files_app.entity.Hotel;
import uz.shdsh.files_app.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/create")
    public String postHotel(@RequestBody Hotel hotel){
        Optional<Hotel> byName = hotelRepository.findByName(hotel.getName());
        if(byName.isPresent()){
            return "This hotel is already exist";
        } else {
            hotelRepository.save(hotel);
            return "Hotel saved";
        }
    }

    @GetMapping
    public Page<Hotel> getHotels(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return hotelRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable int id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        return byId.orElseGet(Hotel::new);
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable int id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(byId.isPresent()){
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        } else {
            return "Hotel not found";
        }
    }

    @PutMapping("/{id}")
    public String editHotel(@RequestBody Hotel hotel, @PathVariable int id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(byId.isPresent()){
            Hotel hotel1 = byId.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Hotel edited";
        } else {
            return "Hotel not found";
        }
    }
}
