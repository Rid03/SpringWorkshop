package school.faang.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.faang.user_service.dto.SkillCandidateDto;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.recommendation.SkillOffer;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.SkillRepository;
import school.faang.user_service.repository.UserRepository;
import school.faang.user_service.repository.UserSkillGuaranteeRepository;
import school.faang.user_service.repository.recommendation.SkillOfferRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final static int MIN_SKILL_OFFERS = 3;
    private final UserRepository userRepository;
    private final SkillOfferRepository skillOfferRepository;

    @Transactional
    public SkillDto create(SkillDto skillDto) throws DataValidationException {
        log.info("Create skill: {}", skillDto);
        validateSkill(skillDto);
        if (skillRepository.existsByTitle(skillDto.getTitle())) {
            throw new DataValidationException("Такой скилл уже содержится в БД");
        }
        Skill savedSkill = skillRepository.save(skillMapper.toEntity(skillDto));
        log.info("Save skill: {}", savedSkill);
        return skillMapper.toDto(savedSkill);
    }

    public List<SkillDto> getUserSkills(long userId) {
        List<Skill> skills = skillRepository.findAllByUserId(userId);
        log.info("Get skills: {}", skills);
        return skillMapper.toDtoList(skills);
    }

    public List<SkillCandidateDto> getOfferedSkills(long userId) {
        List<Skill> skills = skillRepository.findSkillsOfferedToUser(userId);
        Map<String, Long> offersAmount = skills.stream()
                        .collect(Collectors.groupingBy(Skill::getTitle, Collectors.counting()));
        List<SkillCandidateDto> dtos = skillMapper.toSkillCandidateDtoList(skills);
        dtos.forEach(dto -> {
            dto.setOffersAmount(offersAmount.get(dto.getSkill().getTitle()));
        });
        return dtos;
    }

    public SkillDto acquireSkillFromOffers(long skillId, long userId) {
        if (!userRepository.existsById(userId)) {
            throw new DataValidationException("Пользователь не найден");
        }
        Optional<Skill> existingSkill = skillRepository.findUserSkill(skillId, userId);
        if (existingSkill.isPresent()) {
            log.info("User {} already has the skill: {}", userId, skillId);
            return skillMapper.toDto(existingSkill.get());
        }
        List<SkillOffer> skillOffers = skillOfferRepository.findAllOffersOfSkill(skillId, userId);
        if (skillOffers.size() >= MIN_SKILL_OFFERS) {
            Skill newSkill = new Skill();
            newSkill.setId(skillId);
            newSkill.setId(userId);
            Skill savedSkill = skillRepository.save(newSkill);
            log.info("Skill '{}' has been added to user {}", skillId, userId);
            return skillMapper.toDto(savedSkill);
        } else {
            log.info("Skill '{}' has not been added to user {}. Offers count: {}", skillId, userId, skillOffers.size());
            return null;
        }
    }

    private void validateSkill(SkillDto skill) {
        if (skill.getTitle().isEmpty()) {
            log.error("Ошибка валидации названия скилла");
            throw new IllegalArgumentException("skill must have title");
        }
    }
}
