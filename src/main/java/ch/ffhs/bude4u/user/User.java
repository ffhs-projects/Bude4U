package ch.ffhs.bude4u.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String id;

    private Userroles userRole;

    private String firstName;

    private String lastName;

}
