package uz.shdsh.files_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shdsh.files_app.entity.Hotel;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int id;
    private int floor;
    private String size;
    private int hotel_id;
    private int number;
}
