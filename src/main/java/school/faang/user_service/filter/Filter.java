package school.faang.user_service.filter;

import java.util.stream.Stream;

public interface Filter <F, T> {

    boolean isApplicable(F filterDto);

    Stream<T> apply(Stream<T> stream, F filterDto);
}
