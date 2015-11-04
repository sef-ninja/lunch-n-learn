package ch.confte.api;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.confte.api.dao.ExampleDatabaseConfiguration;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

public class ConftechConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
       
    	String databaseUrl = System.getenv("DATABASE_URL");
    	System.out.println("databaseUrl: " + databaseUrl);
    	
        DatabaseConfiguration databaseConfiguration = ExampleDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
        database = databaseConfiguration.getDataSourceFactory(null);

        return database;
    }
    
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}

