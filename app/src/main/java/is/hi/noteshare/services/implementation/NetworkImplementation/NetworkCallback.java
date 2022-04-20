package is.hi.noteshare.services.implementation.NetworkImplementation;

public interface NetworkCallback<T> {

    void onSuccess(T result);

    void onFailure(String errorString);
}
