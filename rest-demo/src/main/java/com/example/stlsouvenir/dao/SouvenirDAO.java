package com.example.stlsouvenir.dao;

import io.dropwizard.hibernate.*;
import org.hibernate.SessionFactory;
import com.example.stlsouvenir.core.Souvenir;
import java.util.List;

public class SouvenirDAO extends AbstractDAO<Souvenir> {
    public SouvenirDAO(SessionFactory factory) {
        super(factory);
    }

    public Souvenir findById(Long id) {
        return get(id);
    }

    public long create(Souvenir souvenir) {
        return persist(souvenir).getId();
    }

    public List<Souvenir> findAll() {
	// TODO: Determine whether this is the correct String literal for the named query
        return list(namedQuery("com.example.stlsouvenir.core.Souvenir.findAll"));
    }
}

