package examtime;

import java.util.HashSet;
import java.util.Set;

public class SocialHandler {

    private Set<String> uniqueHandles;

    public SocialHandler() {
        this.uniqueHandles = new HashSet<>();
    }

    public String checkHandle(String handle) {
        if (handle == null) {
            throw new NullPointerException("Handle cannot be null!!");
        }
        if (handle.isBlank()) {
            throw new IllegalArgumentException("Handle cannot be blank");
        }
        handle = handle.toLowerCase();
        if (handle.length() > 9) {
            handle = handle.substring(0, 9);
        }
        return "@" + handle;
    }

    public boolean addHandle(String handle) {
        String validatedHandle = checkHandle(handle);
        return uniqueHandles.add(validatedHandle);
    }

    public Set<String> getUniqueHandles() {
        return uniqueHandles;
    }

}
