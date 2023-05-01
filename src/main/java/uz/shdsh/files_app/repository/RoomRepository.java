package uz.shdsh.files_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.shdsh.files_app.entity.Hotel;
import uz.shdsh.files_app.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
        Page<Room> findAllByHotel_Id(int hotel_id);
}
