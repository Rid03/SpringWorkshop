package school.faang.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.faang.user_service.dto.UserDto;
import school.faang.user_service.service.MentorshipService;

import java.util.List;

@RestController
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorshipController {
    private final MentorshipService mentorshipService;

    @GetMapping("/get/mentees/{userId}")
    public List<UserDto> getMentees(@PathVariable long userId) {
        return mentorshipService.getMentees(userId);
    }

    @GetMapping("/get/mentors/{userId}")
    public List<UserDto> getMentors(@PathVariable long userId) {
        return mentorshipService.getMentors(userId);
    }

    @DeleteMapping("/delete/mentees/{mentorId}/{userId}")
    public void deleteMentee(@PathVariable long userId, @PathVariable long mentorId) {
        mentorshipService.deleteMentee(userId, mentorId);
    }

    @DeleteMapping("/delete/mentor/{userId}/{mentorId}")
    public void deleteMentor(@PathVariable long userId, @PathVariable long mentorId) {
        mentorshipService.deleteMentor(userId, mentorId);
    }
}
