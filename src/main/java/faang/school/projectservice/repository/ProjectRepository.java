package faang.school.projectservice.repository;

import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(
            "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
                    "FROM Project p " +
                    "WHERE (:ownerId IS NULL OR p.ownerId = :ownerId) AND (:name IS NULL OR p.name = :name)"
    )
    boolean existsByOwnerIdAndName(Long ownerId, String name);

    @Query(
            "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
                    "FROM Project p " +
                    "WHERE (:name IS NULL OR p.name = :name) AND (:status IS NULL OR p.status = :status)"
    )
    List<Project> findByNameAndStatus(String name, ProjectStatus status);
}

