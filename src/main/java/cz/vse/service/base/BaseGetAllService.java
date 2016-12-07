package cz.vse.service.base;

import cz.vse.dto.BaseDTO;

import java.util.List;

public interface BaseGetAllService<DTO extends BaseDTO> {

    List<DTO> getAll();
}
