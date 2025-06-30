package carpooling.eg.profile.Controllers;

import carpooling.eg.profile.DTOs.EnrichResponse;
import carpooling.eg.profile.Models.EntityClasses.Profile;
import carpooling.eg.profile.Services.ProfileService;
import carpooling.eg.profile.DTOs.ProfileRequest;
import carpooling.eg.profile.DTOs.ProfileResponse;
import carpooling.eg.profile.DTOs.GenderResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        log.info("Getting all profiles");
        List<Profile> profiles = profileService.getAllProfiles();
        List<ProfileResponse> responses = profiles.stream()
                .map(ProfileResponse::fromProfile)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ProfileResponse> getProfileByEmail(@PathVariable String email) {
        log.info("Getting profile for email: {}", email);
        Profile profile = profileService.getProfile(email);
        return ResponseEntity.ok(ProfileResponse.fromProfile(profile));
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest request) {
        log.info("Creating new profile for email: {}", request.getEmail());
        Profile profile = profileService.createProfile(request);
        return ResponseEntity.status(201)
                .body(ProfileResponse.fromProfile(profile));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @PathVariable String email,
            @Valid @RequestBody ProfileRequest request) {
        log.info("Updating profile for email: {}", email);
        Profile profile = profileService.updateProfile(email, request);
        return ResponseEntity.ok(ProfileResponse.fromProfile(profile));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String email) {
        log.info("Deleting profile for email: {}", email);
        profileService.deleteProfile(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResponse> getProfileByUserId(@PathVariable String userId) {
        log.info("Getting profile for userId: {}", userId);
        Profile profile = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(ProfileResponse.fromProfile(profile));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<ProfileResponse> updateProfileByUserId(
            @PathVariable String userId,
            @Valid @RequestBody ProfileRequest request) {
        log.info("Updating profile for userId: {}", userId);
        Profile profile = profileService.updateProfileByUserId(userId, request);
        return ResponseEntity.ok(ProfileResponse.fromProfile(profile));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteProfileByUserId(@PathVariable String userId) {
        log.info("Deleting profile for userId: {}", userId);
        profileService.deleteProfileByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gender/{userId}")
    public ResponseEntity<GenderResponse> getUserGender(@PathVariable String userId) {
        log.info("Getting gender for userId: {}", userId);
        String gender = profileService.getUserGender(userId);
        return ResponseEntity.ok(new GenderResponse(userId, gender));
    }

    @GetMapping("/enrich/{userId}")
    public ResponseEntity<EnrichResponse> enrichProfile(@PathVariable String userId) {
        log.info("Enriching profile for userId: {}", userId);
        EnrichResponse profile = profileService.enrichProfile(userId);
        return ResponseEntity.ok(
                new EnrichResponse(
                        profile.getUserId(),
                        profile.getFirstName(),
                        profile.getLastName(),
                        profile.getGender()
                ));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String profile = "test";
        return ResponseEntity.ok(profile);
    }
}
