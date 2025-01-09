package school.faang.user_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.UserDto;
import school.faang.user_service.entity.User;
import school.faang.user_service.mapper.UserMapper;
import school.faang.user_service.repository.mentorship.MentorshipRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorshipService {
    private final MentorshipRepository mentorshipRepository;
    private final UserMapper userMapper;

    public List<UserDto> getMentees(long userId) {
        User mentor = mentorshipRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("Mentees not found"));
        if (mentor.getMentees().isEmpty()) {
            return List.of();
        }
        log.info("Mentees found");
        return mentor.getMentees().stream().
                map(userMapper::toDto).
                toList();
    }

    public List<UserDto> getMentors(long userId) {
        User mentee = mentorshipRepository.findById(userId).
                        orElseThrow(() -> new EntityNotFoundException("Mentees not found"));
        if (mentee.getMentors().isEmpty()) {
            return List.of();
        }
        log.info("Mentors found");
        return mentee.getMentors().stream().
                map(userMapper::toDto).
                toList();
    }

    public void deleteMentee(long userId, long mentorId) {
        User mentor = mentorshipRepository.findById(mentorId)
                .orElseThrow(() -> new EntityNotFoundException("Ментор с ID " + mentorId + " не найден."));
        boolean removed = mentor.getMentees().removeIf(mentee -> mentee.getId() == mentorId);
        if (removed) {
            mentorshipRepository.save(mentor);
            log.info("Менти с ID {} успешно удален у ментора с ID {}", userId, mentorId);
        } else {
            log.warn("Менти с ID {} не найден у ментора с ID {}", userId, mentorId);
        }
    }

    public void deleteMentor(long userId, long mentorId) {
        User mentee = mentorshipRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Менти с ID " + userId + " не найден."));
        boolean removed = mentee.getMentors().removeIf(mentor -> mentor.getId() == userId);
        if (removed) {
            mentorshipRepository.save(mentee);
            log.info("Ментор с ID {} успешно удален у ментора с ID {}", userId, mentorId);
        } else {
            log.warn("Ментор с ID {} успешно удалён у менти с ID {}", mentorId, userId);
        }
    }
}
