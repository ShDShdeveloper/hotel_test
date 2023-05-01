package uz.shdsh.files_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.shdsh.files_app.entity.Hotel;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Optional<Hotel> findByName(String name);

}
