package school.faang.user_service.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.service.SkillService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SkillController.class)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

    @MockBean
    private SkillDto skillDto;

    @Test
    void shouldReturnUserSkills() throws Exception {
        Long userId = 1L;
//        List<SkillDto> skillDtos = List.of(new SkillDto(1L, "Java"), new SkillDto(2L, "Spring"));
//        Mockito.when(skillService.getUserSkills(userId)).thenReturn(skillDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/skill/user/{userId}/skills", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Java"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Spring"));

        Mockito.verify(skillService).getUserSkills(userId);
    }
}