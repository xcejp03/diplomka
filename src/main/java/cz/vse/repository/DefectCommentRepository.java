package cz.vse.repository;

import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface DefectCommentRepository extends BaseRepository<DefectComment> {
    List<DefectComment> findAllDefectCommentsByDefectOrderById(Defect defect);

}
