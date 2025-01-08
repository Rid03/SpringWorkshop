package school.faang.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.faang.user_service.dto.SkillCandidateDto;
import school.faang.user_service.dto.SkillDto;
import school.faang.user_service.service.SkillService;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @PostMapping("/create")
    public SkillDto create(@RequestBody SkillDto skillDto) { // @RequestBody = тело соответствует этому объекту
        return skillService.create(skillDto);
    }

    @GetMapping("/user/{userId}/skills")
    public List<SkillDto> getUserSkills(@PathVariable long userId) { //@PathVariable = используется для Get запросов. Указывает вид пути
        return skillService.getUserSkills(userId);
    }

    @GetMapping("/user/{userId}/offeredskills")
    public List<SkillCandidateDto> getOfferedSkills(@PathVariable long userId) {
        return skillService.getOfferedSkills(userId);
    }

    @GetMapping("/user/{skilled, userId}/acquiredSkill")
    public SkillDto acquireSkillFromOffers(long skillId, long userId) {
        return skillService.acquireSkillFromOffers(skillId, userId);
    }
}
