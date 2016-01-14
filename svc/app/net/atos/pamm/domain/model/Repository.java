package net.atos.pamm.domain.model;

import java.util.List;

public interface Repository<B, I> {
    B set(B businessObject);

    void update(B businessObject);

    B get(I businessObjectId);

    List<B> getAll();

    void remove(B businessObject);
}
