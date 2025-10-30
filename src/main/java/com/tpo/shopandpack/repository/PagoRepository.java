package com.tpo.shopandpack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tpo.shopandpack.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long>{
    // Aquí puedes agregar métodos personalizados si los necesitas
}
