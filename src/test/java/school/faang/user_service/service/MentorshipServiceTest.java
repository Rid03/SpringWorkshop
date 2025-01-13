package school.faang.user_service.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import school.faang.user_service.repository.mentorship.MentorshipRepository;

class MentorshipServiceTest {

    @Mock
    private MentorshipRepository mentorshipRepository;

    @Mock
    private MentorshipService mentorshipService;

    @InjectMocks
    private MentorshipService mentorshipServiceTest;

//    @Test
//    void shouldReturnMenteesWhenExist() {
//        long mentorId = 1L;
//        List<User> mentees = List.of(new User(), new User());
//        Mockito.when(mentorshipRepository.findById(mentorId)).thenReturn();
//    }
}