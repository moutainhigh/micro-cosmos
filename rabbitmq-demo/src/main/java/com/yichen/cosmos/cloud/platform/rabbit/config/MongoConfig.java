package com.yichen.cosmos.cloud.platform.rabbit.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.UnknownHostException;

/**
 * Created by Lizhengxian on 2017/3/20.
 */
@Configuration
@PropertySource("classpath:/mongo.properties")
public class MongoConfig {

    @Value("${mongo.primary.uri}")
    private String primaryUri;

    @Value("${mongo.secondary.uri}")
    private String secondaryUri;

    @Bean(name = "primary")
    public Mongo primaryMongo() throws UnknownHostException {

        MongoClientURI mongoClientURI = new MongoClientURI(primaryUri);
        // configure the client ...
        Mongo mongo = Mongo.Holder.singleton().connect(mongoClientURI);
        return mongo;
    }

    @Bean(name = "secondary")
    public Mongo secondaryMongo() throws UnknownHostException {
        MongoClientURI mongoClientURI = new MongoClientURI(secondaryUri);
        // configure the client ...
        Mongo mongo = Mongo.Holder.singleton().connect(mongoClientURI);
        return mongo;
    }
}
