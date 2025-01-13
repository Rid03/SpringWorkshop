package school.faang.user_service.filter;

import org.springframework.stereotype.Component;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.User;

import java.util.stream.Stream;

@Component
public class UserSkillFilter implements UserFilter {
    @Override
    public boolean isApplicable(UserFilterDto filterDto) {
        return filterDto.getSkillPattern() != null &&
                !filterDto.getSkillPattern().trim().isEmpty();
    }

    @Override
    public Stream<User> apply(Stream<User> stream, UserFilterDto filterDto) {
        return stream.filter(user -> {
            for (Skill skill : user.getSkills()) {
                if (skill.getTitle().toLowerCase().contains(filterDto.getSkillPattern())) {
                    return true;
                }
            }
            return false;
        });
    }
}
