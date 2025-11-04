package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.repository.PackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TiendaServiceTest {

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private TiendaService tiendaService;

    @Test
    void obtenerPrecio_aplica_descuento_porcentaje() {
        Pack pack = new Pack();
        pack.setId(1L);
        pack.setPrecio(200.00);

        when(packageRepository.findById(1L)).thenReturn(Optional.of(pack));

        Double precio = tiendaService.obtenerPrecio(1L, 25, 0); // 25% de descuento

        assertEquals(150.00, precio);
    }
}
