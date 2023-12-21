package src.main.control;

import src.main.entity.Entity;

import java.util.ArrayList;

public interface EntityControl<E> {
    ArrayList<E> getList();
    void add(E entity);
    void delete(E entity);
    void update(E old_entity, E new_entity);
}
