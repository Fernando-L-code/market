package com.exemple.market.persistence.Mapper;

import com.exemple.market.domain.Category;
import com.exemple.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
//            indicamos de donde va hacia donde
        @Mapping(source = "idCategoria",target = "categoryId"),
        @Mapping(source = "descripcion",target = "category"),
        @Mapping(source = "estado",target = "active")
    })
//    conversion de categoria a category
    Category toCategory(Categoria categoria);

//    esto indica la inversa del mappings anterior
    @InheritInverseConfiguration
//    ignoramos productos ya que no la utilizamos en category
    @Mapping(target = "productos",ignore = true)
    Categoria toCategoria(Category category);
}
