package cz.vse.service.base;

import cz.vse.dto.BaseDTO;

import java.util.List;

public interface BaseSaveSeveralService<DTO extends BaseDTO> {

    List<DTO> save(Iterable<DTO> dtos);
}