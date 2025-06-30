package carpooling.eg.profile.Services;

import carpooling.eg.profile.Models.EntityClasses.Profile;
import carpooling.eg.profile.DAOs.ProfileRepository;
import carpooling.eg.profile.Exceptions.ProfileNotFoundException;
import carpooling.eg.profile.Exceptions.DuplicateEmailException;
import carpooling.eg.profile.DTOs.EnrichResponse;
import carpooling.eg.profile.DTOs.ProfileRequest;
import carpooling.eg.profile.DTOs.ProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        log.info("Fetching all profiles");
        return profileRepository.findAll();
    }

    public Profile getProfile(String email) {
        log.info("Fetching profile for email: {}", email);
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with email: " + email));
    }

    public Profile getProfileByUserId(String userId) {
        log.info("Fetching profile for userId: {}", userId);
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with userId: " + userId));
    }

    public Profile createProfile(ProfileRequest request) {
        log.info("Creating new profile for email: {}", request.getEmail());
        if (profileRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Profile already exists with email: " + request.getEmail());
        }
        if (profileRepository.existsByUserId(request.getUserId())) {
            throw new IllegalArgumentException("Profile already exists with userId: " + request.getUserId());
        }

        Profile profile = new Profile(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getUserId(),
            request.getPhoneNumber(),
            request.getGender(),
            request.getImageUrl()
        );

        return profileRepository.save(profile);
    }

    public Profile updateProfile(String email, ProfileRequest request) {
        log.info("Updating profile for email: {}", email);
        Profile profile = getProfile(email);

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setGender(request.getGender());
        profile.setImageUrl(request.getImageUrl());

        return profileRepository.save(profile);
    }

    public Profile updateProfileByUserId(String userId, ProfileRequest request) {
        log.info("Updating profile for userId: {}", userId);
        Profile profile = getProfileByUserId(userId);

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setGender(request.getGender());
        profile.setImageUrl(request.getImageUrl());

        return profileRepository.save(profile);
    }

    public void deleteProfile(String email) {
        log.info("Deleting profile for email: {}", email);
        if (!profileRepository.existsByEmail(email)) {
            throw new ProfileNotFoundException("Profile not found with email: " + email);
        }
        profileRepository.deleteByEmail(email);
    }

    public void deleteProfileByUserId(String userId) {
        log.info("Deleting profile for userId: {}", userId);
        if (!profileRepository.existsByUserId(userId)) {
            throw new ProfileNotFoundException("Profile not found with userId: " + userId);
        }
        Profile profile = getProfileByUserId(userId);
        profileRepository.delete(profile);
    }

    public String getUserGender(String userId) {
        log.info("Fetching gender for userId: {}", userId);
        return getProfileByUserId(userId).getGender();
    }

    public EnrichResponse enrichProfile(String userId) {
        log.info("Enriching profile for userId: {}", userId);
        Profile profile = getProfileByUserId(userId);
        return new EnrichResponse(profile.getUserId(), profile.getFirstName(), profile.getLastName(), profile.getGender());
    }
}
