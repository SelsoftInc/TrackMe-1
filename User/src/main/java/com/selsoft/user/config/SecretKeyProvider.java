package com.selsoft.user.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.parser.clipper.Paths;

@Component
public class SecretKeyProvider {
    /*public byte[] getKey() throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(this.getClass().getResource("/jwt.key").toURI()));
    }*/
}