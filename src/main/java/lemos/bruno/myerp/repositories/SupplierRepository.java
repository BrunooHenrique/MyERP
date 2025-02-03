package lemos.bruno.myerp.repositories;

import lemos.bruno.myerp.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}
