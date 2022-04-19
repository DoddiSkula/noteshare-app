package is.hi.noteshare.services;



public interface NetworkCallback<T> {

    void onSuccess(T result);

    void onFailure(String errorString);
}
