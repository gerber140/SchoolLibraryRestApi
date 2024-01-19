package pl.kurs.schoollibraryrestapi.services;

import org.springframework.stereotype.Service;

import static pl.kurs.schoollibraryrestapi.config.PathConfig.*;

@Service
public class PathBuilderService {
    public String createPath() {
        String userName = System.getProperty("user.name");
        return PATH_START + userName + PATH_END + FILE_name;
    }
}

