package school.faang.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.SkillRepository;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillDto create(SkillDto skillDto) throws DataValidationException {
        validateSkill(skillDto);
        if (skillRepository.existsByTitle(skillDto.getTitle())) {
            throw new DataValidationException("Такой скилл уже содержится в БД");
        }
        Skill savedSkill = skillRepository.save(skillMapper.toEntity(skillDto));
        return skillMapper.toDto(savedSkill);
    }

    private void validateSkill(SkillDto skill) {
        if (skill.getTitle().isEmpty()) {
            throw new IllegalArgumentException("skill must have title");
        }
    }
}
