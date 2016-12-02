package cz.vse.service.base;

import cz.vse.dto.BaseDTO;

public interface BaseGetOneService<DTO extends BaseDTO> {

    DTO getOne(long id);
}
