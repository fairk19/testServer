package dbService;

import java.sql.ResultSet;

public interface TResultHandler<T> {
	T handle(ResultSet resultSet);
}
