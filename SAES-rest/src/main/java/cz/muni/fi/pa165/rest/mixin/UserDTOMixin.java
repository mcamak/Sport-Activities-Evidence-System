package cz.muni.fi.pa165.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Marian Camak on 23. 1. 2016.
 */
@JsonIgnoreProperties({"password"})
public class UserDTOMixin {

}
