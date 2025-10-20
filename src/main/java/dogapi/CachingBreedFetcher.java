package dogapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    private final OkHttpClient client = new OkHttpClient();
    private HashMap<String, List<String>> Data = new HashMap<>();
    private BreedFetcher providedFetcher;
    private int callsMade = 0;

    public CachingBreedFetcher(BreedFetcher fetcher) {
        providedFetcher = fetcher;
    }

    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException {


        if (Data.containsKey(breed)) {
            return Data.get(breed);
        }

        try {
            callsMade++;
            List<String> answer = providedFetcher.getSubBreeds(breed);
            Data.put(breed, answer);

            return answer;


//            Request req = new Request.Builder().url("https://dog.ceo/api/breed/" + breed + "/list").build();
//            Response res = client.newCall(req).execute();
//            String output = res.body().string();
//            JSONObject json = new JSONObject(output);
//
//
//            if (json.get("message").toString() == "Breed not found (main breed does not exist)") {
//                throw new BreedNotFoundException(breed);
//            }
//
//            JSONArray subBreeds = json.getJSONArray("message");
//            ArrayList<String> ans = new ArrayList<String>();
//
//            // note: enhanced-for doesn't work on JSONArray
//            for (int i = 0; i < subBreeds.length(); i++) {
//                ans.add(subBreeds.get(i).toString());
//            }
//
//            Data.put(breed, ans);
//            callsMade++;
//
//            return ans;

        } catch (Exception e) {
            throw new BreedNotFoundException(breed);
        }

    }

    public int getCallsMade() {
        return callsMade;
    }
}