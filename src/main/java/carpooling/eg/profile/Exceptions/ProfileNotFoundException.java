package carpooling.eg.profile.Exceptions;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String email) {
        super("Profile not found with email: " + email);
    }
}
