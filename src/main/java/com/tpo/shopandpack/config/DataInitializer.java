package com.tpo.shopandpack.config;

import com.tpo.shopandpack.repository.UserRepository;
import com.tpo.shopandpack.repository.AlbumRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.emun.UserRol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        crearUsuarios();

        crearAlbums();
    }

    private void crearUsuarios() {

        User user1 = new User();
        user1.setUsername("juan123");
        user1.setEmail("email@example.com");
        user1.setNombre("Juan");
        user1.setApellido("Perez");
        user1.setHashPassword("password1");
        user1.setTelefono("123456789");
        user1.setRole(UserRol.USER);
        user1.setAvatarUrl("http://example.com/avatar1.png");


        userRepository.saveAll(Arrays.asList(user1));
    }

    private void crearAlbums() {

        Album album1 = new Album();
        album1.setTitulo("Album de Futbol 2024");
        album1.setDescripcion("El mejor album de futbol del 2024");
        album1.setCategoria("Deportes");
        album1.setDificultad(Album.Dificultad.MEDIO);
        album1.setPublicado(true);
        album1.setTotalFiguritas(100);
        album1.setCreadorAdmin(userRepository.findByUsername("juan123").get());


        albumRepository.saveAll(Arrays.asList(album1));
    }

    private void crearFiguritas() {}

}