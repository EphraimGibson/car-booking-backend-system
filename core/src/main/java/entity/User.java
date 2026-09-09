package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.StringUtility;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id = UUID.randomUUID().toString();
    private String name;

    public User(String pName) {
        if (StringUtility.isStringNullOrBlank(pName)) {
            throw new IllegalArgumentException("User must have a name, please fill name field");
        }
        name = pName;
    }

    public void setName(String pName) {
        if (StringUtility.isStringNullOrBlank(pName)) {
            throw new IllegalArgumentException("User must have a name, please fill name field");
        }
        this.name = pName;
    }

}