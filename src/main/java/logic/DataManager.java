package logic;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private  Map<Long, ProductList> userData = new HashMap<>();

    public  ProductList getUserData(Long id) {
        ProductList data = this.userData.get(id);
        return data;
    }

    public void setUserData(Long id, ProductList data) {
        this.userData.put(id, data);
    }

    private void removeUserData(Long id) {
        this.userData.remove(id);
    }
}
