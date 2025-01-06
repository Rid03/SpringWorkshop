package school.faang.user_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillDto {
    private Long id;
    private String title;
}
