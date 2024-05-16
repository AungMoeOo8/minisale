package minisale;

public interface SuccessCallback<T> {
	void run(T entity);
}
