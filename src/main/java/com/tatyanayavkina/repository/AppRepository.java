package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.App;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "apps", path = "app")
public interface AppRepository extends PagingAndSortingRepository<App, Long> {

}
