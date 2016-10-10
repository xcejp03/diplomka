
package cz.vse.mapping;


/**
 * Created by pcejka on 10.10.2016.
 */

/*
import ma.glasnost.orika.Mapper;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ma.glasnost.orika.Mapper;
*/
public final class CustomMapperMap {
/*
    private final Map<CustomMapperKey, Mapper<?, ?>> mappersMap = new ConcurrentHashMap<>();

    public CustomMapperMap(Collection<Mapper> mapperCollection) {
        convertToMap(mapperCollection);
    }

    private void convertToMap(Collection<Mapper> mapperCollection) {
        mapperCollection.parallelStream().forEach(mapper -> {
            mappersMap.put(new CustomMapperKey(mapper.getAType().getRawType(), mapper.getBType().getRawType()), mapper);
        });
    }

    public Mapper<?, ?> get(Class aType, Class bType) {
        return mappersMap.get(new CustomMapperKey(aType, bType));
    }

    private static final class CustomMapperKey {

        private final Class<?> aType;
        private final Class<?> bType;

        public CustomMapperKey(Class aType, Class bType) {
            this.aType = aType;
            this.bType = bType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CustomMapperKey that = (CustomMapperKey) o;

            if (!aType.equals(that.aType)) return false;
            return bType.equals(that.bType);

        }

        @Override
        public int hashCode() {
            int result = aType.hashCode();
            result = 31 * result + bType.hashCode();
            return result;
        }
    }
    */
}

