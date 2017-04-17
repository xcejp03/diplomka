package cz.vse.mapping.converter;

import cz.vse.entity.BaseEntity;
import cz.vse.repository.base.BaseRepository;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pcejka on 28.11.2016.
 */
public abstract class AbstractEntityToIdConverter<E extends BaseEntity, ID extends Long> extends BidirectionalConverter<E, ID> {

    @Autowired
    private BaseRepository<E> repository;

    @Override
    public ID convertTo(E source, Type<ID> destinationType) {
        if (source != null) {
            return (ID) source.getId();
        }

        return null;
    }

    @Override
    public E convertFrom(ID source, Type<E> destinationType) {
        if (source != null) {
            return repository.findOne(source);
        }

        return null;
    }


}
