package cz.vse.service.base.impl;

import com.google.common.collect.Lists;
import cz.vse.dto.BaseDTO;
import cz.vse.entity.BaseEntity;
import cz.vse.repository.base.BaseRepository;
import cz.vse.service.base.BaseGetAllService;
import cz.vse.service.base.BaseGetOneService;
import cz.vse.service.base.BaseSaveOneService;
import cz.vse.service.base.BaseSaveSeveralService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommonService<
        GETONEDTO extends BaseDTO,
        GETALLDTO extends BaseDTO,
        SAVEONEDTO extends BaseDTO,
        SAVESEVERALDTO extends BaseDTO,
        E extends BaseEntity>
        implements BaseGetAllService<GETALLDTO>,
        BaseGetOneService<GETONEDTO>,
        BaseSaveOneService<SAVEONEDTO>,
        BaseSaveSeveralService<SAVESEVERALDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    private Class<GETONEDTO> getOneDtoClass;
    private Class<GETALLDTO> getAllDtoClass;
    private Class<SAVEONEDTO> saveOneDtoClass;
    private Class<SAVESEVERALDTO> saveSeveralDtoClass;
    private Class<E> entityClass;
    @Autowired
    private BaseRepository<E> repository;
    @Autowired
    private MapperFacade mapper;

    public AbstractCommonService(Class<GETONEDTO> getOneDtoClass, Class<GETALLDTO> getAllDtoClass, Class<SAVEONEDTO> saveOneDtoClass, Class<SAVESEVERALDTO> saveSeveralDtoClass, Class<E> entityClass) {
        this.getOneDtoClass = getOneDtoClass;
        this.getAllDtoClass = getAllDtoClass;
        this.saveOneDtoClass = saveOneDtoClass;
        this.saveSeveralDtoClass = saveSeveralDtoClass;
        this.entityClass = entityClass;
    }

    @Override
    public List<GETALLDTO> getAll() {
        if (getAllDtoClass != null) {
            List<E> entities = repository.findAll(new Sort(Sort.Direction.ASC, "id"));
            return mapper.mapAsList(entities, getAllDtoClass);
        }

        return new ArrayList<>();
    }

    @Override
    public GETONEDTO getOne(long id) {
        if (getOneDtoClass != null) {
            E entity = repository.findOne(id);
            return mapper.map(entity, getOneDtoClass);
        }

        return null;
    }

    @Override
    public SAVEONEDTO save(SAVEONEDTO dto) {
        if (saveOneDtoClass != null && entityClass != null) {
            E entity = mapper.map(dto, entityClass);

            try {
                entity = repository.save(entity);
            } catch (DataIntegrityViolationException e) {
                l.error("throw new UnexpectedException(e);");
            }

            return mapper.map(entity, saveOneDtoClass);
        }

        return null;
    }

    @Override
    public List<SAVESEVERALDTO> save(Iterable<SAVESEVERALDTO> dtos) {
        if (saveSeveralDtoClass != null && entityClass != null) {
            List<E> saved = mapAndSaveAll(dtos);
            return mapper.mapAsList(saved, saveSeveralDtoClass);
        }

        return new ArrayList<>();
    }

    private List<E> mapAndSaveAll(Iterable<SAVESEVERALDTO> dtos) {
        List<E> saved = new ArrayList<>();

        List<SAVESEVERALDTO> list = Lists.newArrayList(dtos);
        list.stream().forEach(dto -> {
            E entity = mapper.map(dto, entityClass);
            entity = repository.save(entity);
            saved.add(entity);
        });

        return saved;
    }
}
