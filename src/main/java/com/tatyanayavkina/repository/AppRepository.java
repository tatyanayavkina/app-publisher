package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.App;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppRepository extends PagingAndSortingRepository<App, Long> {

}
