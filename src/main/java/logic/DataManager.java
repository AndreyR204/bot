package logic;

import java.util.Map;

public class DataManager {
    private  Map<Long, ProductList> userData;

    public  ProductList getUserData(Long id) {
        ProductList data = userData.get(id);
        return data;
    }

    public void setUserData(Long id, ProductList data) {
        userData.put(id, data);
        userData.remove(id);
    }

    private void removeUserData(Long id) {
        userData.remove(id);
    }
}
