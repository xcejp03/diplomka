package cz.vse.service.base;

import cz.vse.dto.BaseDTO;

public interface BaseSaveOneService<DTO extends BaseDTO> {

    DTO save(DTO dto);
}
