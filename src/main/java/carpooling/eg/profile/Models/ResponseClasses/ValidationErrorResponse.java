package carpooling.eg.profile.Models.ResponseClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, List<String>> errors;
    private String path;
} 