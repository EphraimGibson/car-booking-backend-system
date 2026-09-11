package entity;

import lombok.*;
import utils.StringUtility;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String id;
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

    @Override
    public String toString() {

        return this.getName() + " with id: " + this.id;
    }

}