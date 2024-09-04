package com.example.hibernate.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        /**
        This will help in converting the nested objects as well to entities. If you just return the modelMapper,
                                                            it will not convert the author object and will be null.
        {
            "isbn": "",
            "title": "",
            "author": {
                "id": "",
                "name": "",
                "age": ""
            }
        }
         */
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
