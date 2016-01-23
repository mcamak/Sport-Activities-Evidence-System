package cz.muni.fi.pa165.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marian Camak on 6. 12. 2015.
 */
@RestController
public class MainController {

    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     *
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("users_uri", "/users");

        return Collections.unmodifiableMap(resourcesMap);
    }
}
