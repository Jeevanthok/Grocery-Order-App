package grocery.grocery_app.Exception;

import lombok.Data;

@Data
public class ResourceNotFound extends RuntimeException {
    String resourcename;
    String filedName;
    int fieldValue;

    public ResourceNotFound(String resourcename, String filedName, int fieldValue) {
        super(String.format("User not found %s with field %s and %s",resourcename,filedName,fieldValue));
        this.resourcename = resourcename;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
