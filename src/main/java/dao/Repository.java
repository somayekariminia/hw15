package dao;

public interface Repository<T> {
    public abstract void save(T t);

    public abstract void update(T t);

    public abstract void delete(T t);

    public abstract T getById(int id);

}
