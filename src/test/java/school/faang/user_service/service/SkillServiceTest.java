//package school.faang.user_service.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mapstruct.factory.Mappers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import school.faang.user_service.dto.SkillDto;
//import school.faang.user_service.entity.Skill;
//import school.faang.user_service.mapper.SkillMapper;
//import school.faang.user_service.mapper.SkillMapperImpl;
//import school.faang.user_service.repository.SkillRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ExtendWith(MockitoExtension.class)
//class SkillServiceTest {
//    @Mock
//    private SkillRepository skillRepository;
//    @Mock
//    private SkillMapperImpl skillMapper;
//    @InjectMocks
//    private SkillService skillService;
//
//    @Test
//    void testCreateNewSkill() {
//        SkillDto skillDto = SkillDto.builder() //Подготовили данные
//                .id(15L)
//                .title(" ")
//                .build();
//
//        Mockito.when(skillDto.getId()).thenReturn(15L);
//        Mockito.when(skillDto.getTitle()).thenReturn(" title");
//        Skill result = skillMapper.toEntity(skillDto);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("title", result.getTitle());
//        Assertions.assertEquals(15L, result.getId());
//    }
//
//    @Test
//    void souldReturnUserSkill() {
//        long userId = 1L;
//        List<Skill> skills = List.of(new Skill(), new Skill());
//        List<SkillDto> skillDtos = skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
//
//        Mockito.when(skillRepository.findAllByUserId(userId)).thenReturn(skills);
//        Mockito.when(skillMapper.toDtoList(skills)).thenReturn(skillDtos);
//
//        List<SkillDto> result = skillService.getUserSkills(userId);
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(skillDtos.size(), result.size());
//        Assertions.assertEquals(skillDtos.get(0).getTitle(), result.get(0).getTitle());
//    }
//
//    @Test
//    void getOfferedSkills() {
//        long userId = 1L;
//        List<Skill> skills = List.of(new Skill(), new Skill());
//        List<SkillDto> skillDtos = skills.stream().map(skillMapper::toDto).toList();
//
//        Mockito.when(skillRepository.findAllByUserId(userId)).thenReturn(skills);
//        Mockito.when(skillMapper.toDtoList(skills)).thenReturn(skillDtos);
//
//        Assertions.assertEquals(skillDtos, skillService.getOfferedSkills(userId));
//        Assertions.assertEquals(skillDtos, skillService.getUserSkills(userId));
//    }
//}