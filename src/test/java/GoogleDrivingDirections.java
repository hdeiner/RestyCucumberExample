package test.java;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import us.monoid.web.JSONResource;
import us.monoid.json.JSONArray;
import us.monoid.web.Resty;

public class GoogleDrivingDirections {
	private URL url;
	private URI uri;
	private JSONResource response;
	private JSONArray directionsArray;

    private static final String X_MASHAPE_KEY = "dz76zlrCVimsh0GehwwnBjbZVXdgp1LwLckjsnety8AmdZq63k";

	public GoogleDrivingDirections(String startingPoint, String endingPoint) {

		Resty restCall = new Resty();
        restCall.withHeader("X-Mashape-Key", X_MASHAPE_KEY);
		
		try {
			url = new URL("https://montanaflynn-mapit.p.mashape.com/directions?ending=" + endingPoint+ "&starting=" + startingPoint);
			uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());	
			response = restCall.json(uri);
			directionsArray = new JSONArray(response.get("directions").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

    public String getTotalDistance() throws Exception {
        return response.get("distance").toString();
    }

    public String getTotalTime() throws Exception {
        return response.get("duration").toString();
    }

    public ArrayList<GoogleDrivingDirectionsLeg> getDirections() throws Exception {
        ArrayList<GoogleDrivingDirectionsLeg> directions = new ArrayList<GoogleDrivingDirectionsLeg>();
        directionsArray = new JSONArray(response.get("directions").toString());
        for (int i=0; i<directionsArray.length(); i++) {
            directions.add(new GoogleDrivingDirectionsLeg(
                    i+1,
                    directionsArray.getJSONObject(i).get("distance").toString(),
                    directionsArray.getJSONObject(i).get("duration").toString(),
                    directionsArray.getJSONObject(i).get("direction").toString()
                    ));
        }
        return directions;
    }
}