package com.tpo.shopandpack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.UserSticker;

public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {

    // Devuelve las figuritas que tiene un usuario mediante join sobre UserSticker
    @Query("SELECT us.sticker FROM UserSticker us WHERE us.user.id = :userId")
    List<Sticker> findByUserId(@Param("userId") Long userId);
}