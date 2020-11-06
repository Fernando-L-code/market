package com.exemple.market.persistence;

import com.exemple.market.domain.Product;
import com.exemple.market.domain.repository.ProductRepository;
import com.exemple.market.persistence.Mapper.ProductMapper;
import com.exemple.market.persistence.crud.ProductoCrudRepository;
import com.exemple.market.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//con esto indicamos que esta clase es la que interactua con la base de datos @Repository o @Componet
@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

//    esta notacion sede el control a spring para que cree las instancias correspondientes
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){

        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return  mapper.toProducts(productos);

    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);

        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProduct(int quantity) {
        Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        /* Al no tener un mapper de comvierta una lista de opcionales, generas un ,map de productos y generas una operacion o funcion
        * lamda en donde al mapper toproducts  se le envian los productos, asi el .map  retorna un opcional de lo que esta dentro de
        * lambda*/

        return productos.map(prods-> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
//        findbyid ya retorna un optional por eso no es necesaria la conversion
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto) );
    }


    @Override
    public Product save(Product product) {
        Producto producto=mapper.toProducto(product);
        return mapper.toProduct( productoCrudRepository.save(producto) );
    }

    @Override
    public void  delete(int productId){
 /*   public List<Producto> getByCategoria(int idCategoria){
        return  productoCrudRepository.finByIdCategoriaOrderByNombreAsc(idCategoria);
    }
*/
   /* public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStrockLessThanAndEstado(cantidad,true);
    }*/

//    public Optional<Producto> getProducto(int idProducto){
//        return productoCrudRepository.findById(idProducto);
//    }

//    public Producto  save(Producto producto){
//        return  productoCrudRepository.save(producto);
//    }

        productoCrudRepository.deleteById(productId);
    }
}
