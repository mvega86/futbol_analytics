package com.futbol.analytics.dto.mapper;

import com.futbol.analytics.dto.PartidoDTO;
import com.futbol.analytics.entity.Partido;
import com.futbol.analytics.mapper.IMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
@Builder
public class PartidoToPartidoDTO implements IMapper<Partido, PartidoDTO> {

    @Override
    public PartidoDTO map(Partido in) {
        return new PartidoDTO(
                in.getId(),
                in.getEquipoLocal(),
                in.getEquipoVisitante(),
                in.getGolesLocal(),
                in.getGolesVisitante(),
                in.getFecha()
        );
    }
}
