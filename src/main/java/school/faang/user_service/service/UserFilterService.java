package school.faang.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.User;
import school.faang.user_service.filter.UserFilter;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserFilterService {
    private final List<UserFilter> userFilters;

    public Stream<User> applyFilters(Stream<User> userStream, UserFilterDto userFilterDto) {
        for (UserFilter userFilter : userFilters) {
            if (userFilter.isApplicable(userFilterDto)) {
                userStream = userFilter.apply(userStream, userFilterDto);
            };
        }
        return userStream;
    }
}
