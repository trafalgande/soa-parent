package dto.music_band;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDto {
    private Double x;
    private Double y;
}
