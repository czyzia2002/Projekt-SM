package pl.edu.pb.restauracja;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleSearchTask extends AsyncTask<Void, Void, String> {

    private final String searchQuery;
    private final String apiKey;
    private final String searchEngineId;
    private final OnSearchCompleteListener listener;
    private final Context context;
    private final Activity activity;

    public interface OnSearchCompleteListener {
        void onSearchComplete(List<SearchResult> searchResults);
    }
    public GoogleSearchTask(String searchQuery, String apiKey, String searchEngineId, OnSearchCompleteListener listener, Context context, Activity activity) {
        this.searchQuery = searchQuery;
        this.apiKey = apiKey;
        this.searchEngineId = searchEngineId;
        this.listener = listener;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        String apiUrl = "https://www.googleapis.com/customsearch/v1?q=" + searchQuery + "&key=" + apiKey + "&cx=" + searchEngineId;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                Log.d("GoogleSearchTask", "Google Search API Response: " + result);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonResponse = new JSONObject(result);
            JSONArray itemsArray = jsonResponse.getJSONArray("items");

            List<SearchResult> searchResults = new ArrayList<>();

            // Process each search result item
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);

                String title = item.getString("title");
                String link = item.getString("link");
                String snippet = item.getString("snippet");

                searchResults.add(new SearchResult(title, link, snippet));
            }


            if (listener != null) {
                listener.onSearchComplete(searchResults);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
