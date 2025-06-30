package carpooling.eg.profile.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrichResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
}
