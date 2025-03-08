package com.futbol.analytics.service;

import com.futbol.analytics.dto.PartidoDTO;
import com.futbol.analytics.dto.mapper.PartidoDTOToPartido;
import com.futbol.analytics.dto.mapper.PartidoToPartidoDTO;
import com.futbol.analytics.entity.Partido;
import com.futbol.analytics.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {
    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private PartidoToPartidoDTO partidoToPartidoDTO;

    @Autowired
    private PartidoDTOToPartido partidoDTOToPartido;

    public List<Partido> obtenerPartidos() {
        return partidoRepository.findAll();
    }

    public PartidoDTO guardarPartido(PartidoDTO partidoDTO) {
        Partido partido = partidoDTOToPartido.map(partidoDTO);

        if (partido == null) {
            throw new IllegalArgumentException("El mapeo de PartidoDTO a Partido falló.");
        }

        return partidoToPartidoDTO.map(partidoRepository.save(partido));
    }

    public void eliminarPartido(Long id) {
        partidoRepository.deleteById(id);
    }
}
