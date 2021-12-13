package dto.music_band.request;

import dto.music_band.CoordinatesDto;
import dto.music_band.LabelDto;
import dto.music_band.MusicGenreDto;
import lombok.Data;

@Data
public class UpdateMusicBandRequest {
    private String name;
    private CoordinatesDto coordinates;
    private Long numberOfParticipants;
    private String description;
    private MusicGenreDto genre;
    private LabelDto label;
}
