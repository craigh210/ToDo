package com.cwhcode.todo;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Item, Long> {
}
