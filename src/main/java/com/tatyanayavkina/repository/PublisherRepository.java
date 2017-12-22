package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.Publisher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "publishers", path = "publisher")
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {

}
