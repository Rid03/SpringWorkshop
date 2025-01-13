package school.faang.user_service.filter;

import org.springframework.stereotype.Component;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.User;

import java.util.stream.Stream;

@Component
public class UserEmailFilter implements UserFilter {

    @Override
    public boolean isApplicable(UserFilterDto filterDto) {
        return filterDto.getEmailPattern() != null &&
                !filterDto.getEmailPattern().trim().isEmpty();
    }

    @Override
    public Stream<User> apply(Stream<User> stream, UserFilterDto filterDto) {
        return stream.filter(user -> user.getEmail().toLowerCase()
                .contains(filterDto.getEmailPattern().trim().toLowerCase()));
    }
}
