package dto.music_band.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthUserRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
