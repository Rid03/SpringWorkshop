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

    @GetMapping("/get/mentees")
    public List<UserDto> getMentees(@PathVariable long userId) {
        return mentorshipService.getMentees(userId);
    }

    @GetMapping("/get/mentors")
    public List<UserDto> getMentors (@PathVariable long userId) {
        return mentorshipService.getMentors(userId);
    }

    @PostMapping("/delete/mentees")
    public void deleteMentee (@PathVariable long userId, @PathVariable long mentorId) {
        mentorshipService.deleteMentee(userId, mentorId);
    }

    @PostMapping("/delete/mentor")
    public void deleteMentor (@PathVariable long userId, @PathVariable long mentorId) {
        mentorshipService.deleteMentor(userId, mentorId);
    }
}
