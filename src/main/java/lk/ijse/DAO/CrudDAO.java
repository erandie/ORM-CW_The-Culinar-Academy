package lk.ijse.DAO;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> {
    public boolean add(T entity);
    public boolean update(T entity);
    public boolean delete(String id);
    public T search(String id);
    public List<T> getAll();
    public String generateNewID();
    public boolean exist(String id);
}
