package com.stratpoint.android.retro;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface RetrofitInterface {
    @GET("v2/5e4e5fed2f00004e0016a69f")
    public Call<List<ApiObject>> getAllPost();
}
