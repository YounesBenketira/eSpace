package graphical.wireless.espace.ui.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteDao {
    @Query("SELECT * FROM Favourite")
    LiveData<List<Favourite>> getAll();

    @Query("SELECT * FROM Favourite WHERE uid IN (:userIds)")
    LiveData<List<Favourite>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Favourite WHERE title LIKE :title LIMIT 1")
    LiveData<Favourite> findByTitle(String title);

    @Insert
    void insertAll(List<Favourite> fav);

    @Insert
    void insert(Favourite fav);

    @Delete
    void delete(Favourite fav);
}
