package petStore.utils;

import petStore.pojo.Pet;
import petStore.pojo.Store;
import petStore.pojo.User;

public class TestRunContext {

    private static Pet pet;
    private static Store store;
    private static User user;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        TestRunContext.pet = pet;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        TestRunContext.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        TestRunContext.user = user;
    }
}
