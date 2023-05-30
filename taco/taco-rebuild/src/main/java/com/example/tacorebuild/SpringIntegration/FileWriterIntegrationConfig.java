package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {

    @Bean
    @Transformer(inputChannel = "textInChannel",outputChannel = "fileWriterChannel")
    public GenericTransformer<String,String> upCaseTrans(){
        return text-> text.toUpperCase();
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter(){
        FileWritingMessageHandler fRmH=new FileWritingMessageHandler(new File("/tmp/files/"));
        fRmH.setExpectReply(false);
        fRmH.setFileExistsMode(FileExistsMode.APPEND);
        fRmH.setAppendNewLine(true);
        return fRmH;
    }

}
