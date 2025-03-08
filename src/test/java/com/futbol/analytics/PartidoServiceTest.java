package com.futbol.analytics;

import com.futbol.analytics.dto.PartidoDTO;
import com.futbol.analytics.dto.mapper.PartidoDTOToPartido;
import com.futbol.analytics.dto.mapper.PartidoToPartidoDTO;
import com.futbol.analytics.entity.Partido;
import com.futbol.analytics.repository.PartidoRepository;
import com.futbol.analytics.service.PartidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartidoServiceTest {

    @Mock
    private PartidoRepository partidoRepository;

    @Mock
    private PartidoToPartidoDTO partidoToPartidoDTO;

    @Mock
    private PartidoDTOToPartido partidoDTOToPartido;

    @InjectMocks
    private PartidoService partidoService;

    private Partido partido;
    private PartidoDTO partidoDTO;

    @BeforeEach
    void setUp() {
        partido = new Partido();
        partido.setId(1L);
        partido.setEquipoLocal("Equipo A");
        partido.setEquipoVisitante("Equipo B");
        partido.setGolesLocal(2);
        partido.setGolesVisitante(1);

        partidoDTO = new PartidoDTO();
        partidoDTO.setEquipoLocal("Equipo A");
        partidoDTO.setEquipoVisitante("Equipo B");
        partidoDTO.setGolesLocal(2);
        partidoDTO.setGolesVisitante(1);
    }

    @Test
    void testObtenerPartidos() {
        when(partidoRepository.findAll()).thenReturn(Arrays.asList(partido));
        List<Partido> resultado = partidoService.obtenerPartidos();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    void testGuardarPartido() {
        when(partidoDTOToPartido.map(any(PartidoDTO.class))).thenReturn(partido);
        when(partidoRepository.save(any(Partido.class))).thenReturn(partido);
        when(partidoToPartidoDTO.map(any(Partido.class))).thenReturn(partidoDTO);

        PartidoDTO resultado = partidoService.guardarPartido(partidoDTO);
        assertNotNull(resultado);
        assertEquals("Equipo A", resultado.getEquipoLocal());
        assertEquals("Equipo B", resultado.getEquipoVisitante());
    }

    @Test
    void testEliminarPartido() {
        doNothing().when(partidoRepository).deleteById(1L);
        partidoService.eliminarPartido(1L);
        verify(partidoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGuardarPartidoConMapperNulo() {
        when(partidoDTOToPartido.map(any(PartidoDTO.class))).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> partidoService.guardarPartido(partidoDTO));
    }
}
