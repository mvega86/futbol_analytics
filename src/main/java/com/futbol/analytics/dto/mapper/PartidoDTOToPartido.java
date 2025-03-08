package com.futbol.analytics.dto.mapper;

import com.futbol.analytics.dto.PartidoDTO;
import com.futbol.analytics.entity.Partido;
import com.futbol.analytics.mapper.IMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
public class PartidoDTOToPartido implements IMapper<PartidoDTO, Partido> {

    @Override
    public Partido map(PartidoDTO in) {
        return new Partido(
                null,
                in.getEquipoLocal(),
                in.getEquipoVisitante(),
                in.getGolesLocal(),
                in.getGolesVisitante(),
                in.getFecha()
        );
    }
}
