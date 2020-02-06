package xcini.rv.com.xcini;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import retrofit2.Call;
import retrofit2.Callback;
import xcini.rv.com.xcini.Beans.PlaceDetails_Pojo;
import xcini.rv.com.xcini.Beans.PlacePredictions_Pojo;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Utils;
import xcini.rv.com.xcini.Utils.Web_Service;

public class Home extends AppCompatActivity implements View.OnClickListener, CommonFunctions, OnMapReadyCallback {
    DrawerLayout li_main;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Location loc;
    TextView tv_pickup;
    EditText ed_location;
    ListView searchResultLV;
    private Handler handler;
    String temp, latitude, longitude;

    private List<PlacePredictions_Pojo.Predictions> placesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Utils.show_progress_bar(Home.this);
        findViews();
        parseIntentData();
        setData();
        parseIntentData();
        setOnClicks();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                li_main.openDrawer(Gravity.START);
                break;
        }
    }

    @Override
    public void setData() {
        temp = "";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        } else {

            SmartLocation.with(this).location().oneFix()
                    .start(new OnLocationUpdatedListener() {
                        @Override
                        public void onLocationUpdated(Location locatin) {
                            loc = locatin;
                            mapFragment.getMapAsync(Home.this);
                            tv_pickup.setText(getAddress(locatin.getLatitude(), locatin.getLongitude()));
                        }
                    });
        }
        placesList = new ArrayList<>();
        ed_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    if (ed_location.getText().length() > 2) {
                        if (handler != null) {
                            searchResultLV.setVisibility(View.VISIBLE);
                            handler.removeCallbacksAndMessages(null);
                        } else {
                            handler = new Handler();
                        }
                        new ExternalThread(count, s).execute();
                    } else {
                        searchResultLV.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        searchResultLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ed_location.setText(placesList.get(position).getDescription());
                temp = placesList.get(position).getDescription();
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getWindow().getDecorView().getApplicationWindowToken()), 0);

                if (ed_location.getText().toString().equals(temp)) {
                    searchResultLV.setVisibility(View.GONE);

                    Call<PlaceDetails_Pojo> call = new Web_Service().getApiService().getLocationDetails(getPlaceDetailsUrl(placesList.get(position).getPlace_id()));
                    call.enqueue(new Callback<PlaceDetails_Pojo>() {
                        @Override
                        public void onResponse(Call<PlaceDetails_Pojo> call, retrofit2.Response<PlaceDetails_Pojo> response) {
                            if (response.body().getStatus().equals("OK")) {
                                try {
                                    PlaceDetails_Pojo.Result.Geometry.Location location = new PlaceDetails_Pojo().new Result().new Geometry().new Location();
                                    location.setLat(response.body().getResult().getGeometry().getLocation().getLat());
                                    location.setLng(response.body().getResult().getGeometry().getLocation().getLng());
                                    latitude = location.getLat();
                                    longitude = location.getLng();

                                    LatLng origin = new LatLng(loc.getLatitude(), loc.getLongitude());
                                    LatLng dest = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                                    // Getting URL to the Google Directions API
                                    String url = getDirectionsUrl(origin, dest);

                                    DownloadTask downloadTask = new DownloadTask();

                                    // Start downloading json data from Google Directions API
                                    downloadTask.execute(url);

                                } catch (Exception e) {
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PlaceDetails_Pojo> call, Throwable t) {
                            Log.e("aa", "-=-=-=-=" + t.getMessage());
                        }
                    });
                } else {
                    searchResultLV.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.img_menu).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        tv_pickup = findViewById(R.id.tv_pickup);
        searchResultLV = findViewById(R.id.searchResultLV);
        ed_location = findViewById(R.id.ed_location);
        li_main = findViewById(R.id.li_main);
    }

    @Override
    public void parseIntentData() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Utils.hide_progress_bar();
        if (mMap == null) {
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
                return;
            }
            googleMap.setMyLocationEnabled(true);


            LatLng myLatLng = new LatLng(loc.getLatitude(),
                    loc.getLongitude());

            CameraPosition myPosition = new CameraPosition.Builder().target(myLatLng).zoom(17).bearing(90).tilt(30).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
        }
    }


    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);
//            add = add + "\n" + obj.getCountryName();
//            add = add + "\n" + obj.getCountryCode();
//            add = add + "\n" + obj.getAdminArea();
            add = add + ", " + obj.getPostalCode();
//            add = add + "\n" + obj.getSubAdminArea();
//            add = add + ", " + obj.getLocality();
//            add = add + "\n" + obj.getSubThoroughfare();

            return add;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public String getPlaceAutoCompleteUrl(String input) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/place/autocomplete/json");
        urlString.append("?input=");
        try {
            urlString.append(URLEncoder.encode(input, "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlString.append("&loc=");
        urlString.append(loc.getLatitude() + "," + loc.getLongitude());
//        urlString.append("&radius=500&language=en");
        urlString.append("&key=" + "AIzaSyDDfWNtHlQ9VE5xe-lELER8j--VEqqTbUg");
        Log.d("FINAL URL:::   ", urlString.toString());
        return urlString.toString();
    }

    public String getPlaceDetailsUrl(String input) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/place/details/json?placeid=");
        urlString.append(input);
        urlString.append("&key=" + "AIzaSyDDfWNtHlQ9VE5xe-lELER8j--VEqqTbUg");
        Log.d("FINAL URL:::   ", urlString.toString());
        return urlString.toString();
    }

    public class ExternalThread extends AsyncTask<String, String, String> {
        String txt = "";
        CharSequence s;
        private int count;

        ExternalThread(int count, CharSequence s) {
            this.count = count;
            this.s = s;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txt = ed_location.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            if (count != 0) {
                if (txt.length() > 2) {
                    Call<PlacePredictions_Pojo> call = new Web_Service().getApiService().getLocations(getPlaceAutoCompleteUrl(txt));
                    call.enqueue(new Callback<PlacePredictions_Pojo>() {
                        @Override
                        public void onResponse(Call<PlacePredictions_Pojo> call, retrofit2.Response<PlacePredictions_Pojo> response) {
                            if (response.body().getStatus().equals("OK")) {
                                placesList.clear();
                                for (int i = 0; i < response.body().getPredictions().size(); i++) {
                                    PlacePredictions_Pojo.Predictions.StructuredFormatting structuredFormatting = new PlacePredictions_Pojo().new Predictions().new StructuredFormatting();
                                    structuredFormatting.setMain_text(response.body().getPredictions().get(i).getStructured_formatting().getMain_text());
                                    PlacePredictions_Pojo.Predictions predictions = new PlacePredictions_Pojo().new Predictions();
                                    predictions.setDescription(response.body().getPredictions().get(i).getDescription());
                                    predictions.setPlace_id(response.body().getPredictions().get(i).getPlace_id());
                                    predictions.setStructured_formatting(structuredFormatting);
                                    placesList.add(predictions);
                                }
                                searchResultLV.setAdapter(new AutoCompleteAdapter(placesList, Home.this));
                            }
                        }

                        @Override
                        public void onFailure(Call<PlacePredictions_Pojo> call, Throwable t) {
                            Log.e("aa", "-=-=-=-=" + t.getMessage());
                        }
                    });
                    temp = s.toString();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public class DirectionsJSONParser {

        /**
         * Receives a JSONObject and returns a list of lists containing latitude and longitude
         */
        public List<List<HashMap<String, String>>> parse(JSONObject jObject) {

            List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String, String>>>();
            JSONArray jRoutes = null;
            JSONArray jLegs = null;
            JSONArray jSteps = null;

            try {

                jRoutes = jObject.getJSONArray("routes");

                /** Traversing all routes */
                for (int i = 0; i < jRoutes.length(); i++) {
                    jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                    List path = new ArrayList<HashMap<String, String>>();

                    /** Traversing all legs */
                    for (int j = 0; j < jLegs.length(); j++) {
                        jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                        /** Traversing all steps */
                        for (int k = 0; k < jSteps.length(); k++) {
                            String polyline = "";
                            polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                            List<LatLng> list = decodePoly(polyline);

                            /** Traversing all points */
                            for (int l = 0; l < list.size(); l++) {
                                HashMap<String, String> hm = new HashMap<String, String>();
                                hm.put("lat", Double.toString(((LatLng) list.get(l)).latitude));
                                hm.put("lng", Double.toString(((LatLng) list.get(l)).longitude));
                                path.add(hm);
                            }
                        }
                        routes.add(path);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }

            return routes;
        }

        /**
         * Method to decode polyline points
         * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
         */
        private List<LatLng> decodePoly(String encoded) {

            List<LatLng> poly = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }

            return poly;
        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            mMap.clear();
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(4);
                lineOptions.color(getResources().getColor(R.color.colorPrimary));
            }
            CameraPosition myPosition = new CameraPosition.Builder().target(new LatLng(loc.getLatitude(), loc.getLongitude())).zoom(8).bearing(90).tilt(30).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }

}
