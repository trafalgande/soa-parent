package dto.music_band.response;

import dto.music_band.CoordinatesDto;
import dto.music_band.LabelDto;
import dto.music_band.MusicGenreDto;
import lombok.Data;

@Data
public class MusicBandView {
    private String name;
    private CoordinatesDto coordinates;
    private Long numberOfParticipants;
    private String description;
    private MusicGenreDto genre;
    private LabelDto label;
}
