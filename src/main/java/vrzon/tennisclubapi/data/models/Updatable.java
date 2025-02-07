package vrzon.tennisclubapi.data.models;

public interface Updatable<T extends BaseModel<T>> {
    void updateWith(T updatingObject);
}
