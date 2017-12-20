package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.AppVersion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppVersionRepository extends PagingAndSortingRepository<AppVersion, Long> {

}
