package xcini.rv.com.xcini.Beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 6/6/2017.
 */

public class PlaceDetails_Pojo {
    @SerializedName("result")
    PlaceDetails_Pojo.Result result;

    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        @SerializedName("geometry")
        PlaceDetails_Pojo.Result.Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public class Geometry {
            @SerializedName("location")
            PlaceDetails_Pojo.Result.Geometry.Location location;

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public class Location {
                @SerializedName("lat")
                String lat;

                @SerializedName("lng")
                String lng;

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }
            }
        }
    }

}

