package com.exemple.market.persistence.crud;

import com.exemple.market.persistence.entity.Producto;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Aqui se escriben metodos que los repositorios de sprin data no nos pueden dar
//Podemos regresar datos del tipo optional para hacerlo mas funcional
public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {

//    @Query(value = "SELECT* FROM productos WHERE id_categoria=?",nativeQuery = true)
//    Estos dos son lo mismo
    List<Producto>findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
