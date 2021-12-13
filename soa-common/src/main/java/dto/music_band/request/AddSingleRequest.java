package dto.music_band.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddSingleRequest {
    @NotNull
    private String title;
}
