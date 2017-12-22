package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.ReleaseManager;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "releaseManagers", path = "release-manager")
public interface ReleaseManagerRepository extends PagingAndSortingRepository<ReleaseManager, Long> {

}
