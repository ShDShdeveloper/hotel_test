package uz.shdsh.files_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.shdsh.files_app.dto.RoomDto;
import uz.shdsh.files_app.entity.Hotel;
import uz.shdsh.files_app.entity.Room;
import uz.shdsh.files_app.repository.HotelRepository;
import uz.shdsh.files_app.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/save")
    public String saveRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> byId = hotelRepository.findById(roomDto.getHotel_id());
        if(byId.isPresent()){
            Room room = new Room();
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setNumber(roomDto.getNumber());
            room.setHotel(byId.get());
            roomRepository.save(room);
            return "Room saved";
        } else {
            return "Hotel not found";
        }
    }

    @GetMapping
    public Page<Room> getRooms(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable int id){
        Optional<Room> byId = roomRepository.findById(id);
        return byId.orElseGet(Room::new);
    }

    @GetMapping("/byhotel/{hotelid}")
    public Page<Room> getRoomByHotelId(@PathVariable int hotelid, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAllByHotel_Id(hotelid);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable int id){
        try{
            roomRepository.deleteById(id);
            return "Room deleted";
        } catch (Exception e){
            return "Xatolik";
        }
    }

    @PutMapping("/{editingroomid}")
    public String editRoom(@PathVariable int editingroomid, @RequestBody RoomDto roomDto){
        Optional<Room> byId = roomRepository.findById(editingroomid);
        if(byId.isPresent()){
            Optional<Hotel> byId1 = hotelRepository.findById(roomDto.getHotel_id());
            if(byId1.isPresent()){
                Room room = byId.get();
                room.setHotel(byId1.get());
                room.setSize(roomDto.getSize());
                room.setFloor(roomDto.getFloor());
                room.setNumber(roomDto.getNumber());
                roomRepository.save(room);
                return "Room edited";
            }
            return "Hotel not found";
        }
        return "Room not found";
    }
}
