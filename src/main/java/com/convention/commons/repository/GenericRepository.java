package com.convention.commons.repository;

import com.convention.commons.pagination.ListPagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T,ID> extends JpaRepository<T,ID> {
    default ListPagination<T> findPagination(Integer pageIndex,Integer pageQuantity) {
        Page page = findAll(PageRequest.of(pageIndex,pageQuantity));
        return new ListPagination<T>(page.getContent(),page.getTotalElements(),page.getNumber());
    }

}
