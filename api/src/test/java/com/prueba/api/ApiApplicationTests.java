package com.prueba.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import com.prueba.api.entities.Nave;
import com.prueba.api.repositories.INaveRepository;
import com.prueba.api.services.NaveServiceImp;

@SpringBootTest
@EnableCaching
class ApiApplicationTests {

    @Mock
    private INaveRepository naveRepository;

    @InjectMocks
    private NaveServiceImp naveService;

    @Test
    void testCacheableMethod() {
        MockitoAnnotations.openMocks(this);

        Long id = 1L;
        Nave nave = new Nave(id, "Aerodeslizador t-47");
        when(naveRepository.findById(id)).thenReturn(Optional.of(nave));

        // Primera llamada: se almacena en caché
        naveService.findById(id);
        // Segunda llamada: se debería obtener del caché
        naveService.findById(id);

        // Verifica que el método `findById` del repositorio solo se llamó una vez
        verify(naveRepository, times(1)).findById(id);
    }
}

