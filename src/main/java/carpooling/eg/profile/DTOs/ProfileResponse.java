package carpooling.eg.profile.DTOs;

import java.time.LocalDateTime;
import carpooling.eg.profile.Models.EntityClasses.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProfileResponse fromProfile(Profile profile) {
        return new ProfileResponse(
            profile.getUserId(),
            profile.getEmail(),
            profile.getFirstName(),
            profile.getLastName(),
            profile.getPhoneNumber(),
            profile.getGender(),
            profile.getImageUrl(),
            profile.getCreatedAt(),
            profile.getUpdatedAt()
        );
    }
} 