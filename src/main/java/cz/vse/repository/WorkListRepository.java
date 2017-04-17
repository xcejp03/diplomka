package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.WorkList;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkListRepository extends BaseRepository<WorkList> {
    List<WorkList> findAllWorkListDTOByAuthor(Person person);

    List<WorkList> findAllWorkListDTOByProjectIn(List<Project> projects);


    @Query("select w from WorkList w join w.project p join p.personMembers pm where w.plannedExecution = :plannedExecution and pm.id = :personId ")
    List<WorkList> findAllWorkListDTOByMemberToday(@Param("plannedExecution") LocalDate plannedExecution, @Param("personId") long personId);


    @Query("select w from WorkList w join w.project p join p.personMembers pm where  pm.id = :personId and w.plannedExecution between :dayStart and :dayEnd")
    List<WorkList> findAllWorkListDTOByMemberBetweenDays(@Param("personId") long personId,
                                                         @Param("dayStart") LocalDate dayStart,
                                                         @Param("dayEnd") LocalDate dayEnd);
}
