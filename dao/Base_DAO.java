package dao;

public abstract class Base_DAO<T> {

    public abstract void insert(T obj);

    public abstract void update(T obj);

    public abstract void delete(int id);
}
