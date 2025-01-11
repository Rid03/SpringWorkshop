package school.faang.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import school.faang.user_service.dto.SkillCandidateDto;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.entity.Skill;

import java.util.List;

@Mapper(componentModel = "Spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SkillMapper {

    SkillDto toDto(Skill skill);
    Skill toEntity(SkillDto skillDto);
    List<SkillDto> toDtoList(List<Skill> skillList);
    List<Skill> toEntity(List<SkillDto> skillDtoList);
    SkillCandidateDto toSkillCandidateDto(Skill skill);
    List<SkillCandidateDto> toSkillCandidateDtoList(List<Skill> skills);
}
