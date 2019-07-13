package com.ac.microservices.dispatcher.repository;

import com.ac.microservices.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EventRepository<T extends Event> extends CrudRepository<T, String> {
}
