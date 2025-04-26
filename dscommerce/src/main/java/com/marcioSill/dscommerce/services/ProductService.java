package com.marcioSill.dscommerce.services;

import com.marcioSill.dscommerce.entities.Product;
import com.marcioSill.dscommerce.repositories.ProductRepository;
import com.marcioSill.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.marcioSill.dscommerce.dto.ProductDTO;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable); //busta todos oa registros do banco de dados
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product entity = new Product();
        /*entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());*/
        //Criado um metodo auxiliar para fazer a acopia para a entidade
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity); // salva os dados no banco
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(long id, ProductDTO dto){

        Product entity = repository.getReferenceById(id);
       /* entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());*/
        //Criado um metodo auxiliar para fazer a acopia para a entidade

        copyDtoToEntity(dto, entity);
        entity = repository.save(entity); // salva os dados no banco
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }
}
