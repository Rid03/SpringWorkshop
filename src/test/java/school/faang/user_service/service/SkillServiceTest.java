package school.faang.user_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;
    @Mock
    private SkillMapper skillMapper = Mappers.getMapper(SkillMapper.class);
    @InjectMocks
    private SkillService skillService;

    @Test
    void create() {
        SkillDto skillDto = SkillDto.builder() //Подготовили данные
                .id(15L)
                .title(" ")
                .build();

        Skill skill = skillMapper.toEntity(skillDto);


    }
}