package com.bobvu.tinhong.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchClientConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
    
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("128.199.171.126", 9200, "http")));

        return client;
    }
}