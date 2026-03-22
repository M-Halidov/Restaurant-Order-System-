package bg.tu_varna.sit.f24621696.interfaces;

import java.util.List;

public interface RepoInterface<T> {
    void add(T t);
    void remove(int ID);
    List<T> getList();
}
