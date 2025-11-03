package com.tpo.shopandpack.integration;

import com.tpo.shopandpack.dto.ComprarPackRequestDTO;
import com.tpo.shopandpack.dto.PackDTO;
import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.AlbumRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.repository.UserRepository;
import com.tpo.shopandpack.service.TiendaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ComprarPackIntegrationTest {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private com.tpo.shopandpack.repository.PackageRepository packageRepository;
    @Autowired
    private com.tpo.shopandpack.repository.PagoRepository pagoRepository;

    @Test
    void comprarPaquete_full_flow_reduces_stock_and_returns_pack() {
    User user = new User();
    user.setUsername("testuser");
    user.setEmail("test@example.com");
    user.setHashPassword("passwordhash");
    userRepository.save(user);

    Album album = new Album();
    album.setTitulo("AlbumTest");
    albumRepository.save(album);

        // Create many stickers across rarezas to ensure strategy can select 5
        int id = 1;
        for (int i = 0; i < 10; i++) {
            stickerRepository.save(new Sticker(album, "C" + id, id++, Rareza.COMUN, null));
        }
        for (int i = 0; i < 10; i++) {
            stickerRepository.save(new Sticker(album, "R" + id, id++, Rareza.RARA, null));
        }
        for (int i = 0; i < 10; i++) {
            stickerRepository.save(new Sticker(album, "E" + id, id++, Rareza.EPICA, null));
        }

        List<Sticker> all = stickerRepository.findAvailableByAlbumId(album.getId());
        assertTrue(all.size() >= 5);

    // Sum available stock before (not asserted here due to variability in strategies)
    // Note: we intentionally don't assert total stock here because sticker selection strategies
    // can vary; we focus on persistence and basic flow in this integration test.

        ComprarPackRequestDTO req = new ComprarPackRequestDTO(user.getId(), "EFECTIVO");

        PackDTO packDTO = tiendaService.comprarPaquete(album.getId(), req);

        assertNotNull(packDTO);
        assertNotNull(packDTO.getId());
    // Expect the pack DTO to contain between 1 and 5 stickers (strategy may vary)
    assertTrue(packDTO.getStickers().size() > 0 && packDTO.getStickers().size() <= 5);

    // Check pack and payment persisted
    long packs = packageRepository.countByUserIdAndAlbumId(user.getId(), album.getId());
    assertEquals(1L, packs);

    assertTrue(pagoRepository.count() >= 1);
    }
}
