//package school.faang.user_service.service;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import school.faang.user_service.entity.User;
//import school.faang.user_service.repository.UserRepository;
//import school.faang.user_service.repository.mentorship.MentorshipRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MentorshipServiceTest {
//
//    @Mock
//    private MentorshipRepository mentorshipRepository;
//
//    @Mock
//    private MentorshipService mentorshipService;
//
//    @InjectMocks
//    private MentorshipService mentorshipServiceTest;
//
//    @Test
//    void shouldReturnMenteesWhenExist() {
//        long mentorId = 1L;
//        List<User> mentees = List.of(new User(), new User());
//        Mockito.when(mentorshipRepository.findById(mentorId)).thenReturn(mentees);
//    }
//}