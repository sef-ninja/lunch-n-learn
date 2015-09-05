package com.example.stlsouvenir.resources;

import io.dropwizard.hibernate.UnitOfWork;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;
import com.example.stlsouvenir.dao.*;
import com.example.stlsouvenir.core.*;

@Path("/souvenir/{souvenirId}")
@Produces(MediaType.APPLICATION_JSON)
public class SouvenirResource {
    private final AtomicLong counter;
    private SouvenirDAO souvenirDao;

    public SouvenirResource(SouvenirDAO souvenirDao) {
        this.counter = new AtomicLong();
	this.souvenirDao = souvenirDao;
    }

    @GET
    @Timed
    @UnitOfWork
    public Souvenir getById(@PathParam("souvenirId") long souvenirId) {
        return souvenirDao.findById(souvenirId);
    }
}
