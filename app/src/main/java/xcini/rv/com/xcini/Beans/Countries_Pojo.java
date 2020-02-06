package xcini.rv.com.xcini.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 3/1/18.
 */

public class Countries_Pojo {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public class Country {

        @SerializedName("object_type")
        @Expose
        private String objectType;
        @SerializedName("country")
        @Expose
        private Country_ country;

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public Country_ getCountry() {
            return country;
        }

        public void setCountry(Country_ country) {
            this.country = country;
        }

        public class Country_ {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("iso")
            @Expose
            private String iso;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("nice_name")
            @Expose
            private String niceName;
            @SerializedName("iso3")
            @Expose
            private String iso3;
            @SerializedName("num_code")
            @Expose
            private Integer numCode;
            @SerializedName("phone_code")
            @Expose
            private Integer phoneCode;
            @SerializedName("international_phone_code")
            @Expose
            private String internationalPhoneCode;
            @SerializedName("availability")
            @Expose
            private String availability;
            @SerializedName("images")
            @Expose
            private Images images;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNiceName() {
                return niceName;
            }

            public void setNiceName(String niceName) {
                this.niceName = niceName;
            }

            public String getIso3() {
                return iso3;
            }

            public void setIso3(String iso3) {
                this.iso3 = iso3;
            }

            public Integer getNumCode() {
                return numCode;
            }

            public void setNumCode(Integer numCode) {
                this.numCode = numCode;
            }

            public Integer getPhoneCode() {
                return phoneCode;
            }

            public void setPhoneCode(Integer phoneCode) {
                this.phoneCode = phoneCode;
            }

            public String getInternationalPhoneCode() {
                return internationalPhoneCode;
            }

            public void setInternationalPhoneCode(String internationalPhoneCode) {
                this.internationalPhoneCode = internationalPhoneCode;
            }

            public String getAvailability() {
                return availability;
            }

            public void setAvailability(String availability) {
                this.availability = availability;
            }

            public Images getImages() {
                return images;
            }

            public void setImages(Images images) {
                this.images = images;
            }

            public class Images {

                @SerializedName("128x128")
                @Expose
                private String _128x128;
                @SerializedName("16x16")
                @Expose
                private String _16x16;
                @SerializedName("24x24")
                @Expose
                private String _24x24;
                @SerializedName("32x32")
                @Expose
                private String _32x32;
                @SerializedName("48x48")
                @Expose
                private String _48x48;
                @SerializedName("64x64")
                @Expose
                private String _64x64;

                public String get128x128() {
                    return _128x128;
                }

                public void set128x128(String _128x128) {
                    this._128x128 = _128x128;
                }

                public String get16x16() {
                    return _16x16;
                }

                public void set16x16(String _16x16) {
                    this._16x16 = _16x16;
                }

                public String get24x24() {
                    return _24x24;
                }

                public void set24x24(String _24x24) {
                    this._24x24 = _24x24;
                }

                public String get32x32() {
                    return _32x32;
                }

                public void set32x32(String _32x32) {
                    this._32x32 = _32x32;
                }

                public String get48x48() {
                    return _48x48;
                }

                public void set48x48(String _48x48) {
                    this._48x48 = _48x48;
                }

                public String get64x64() {
                    return _64x64;
                }

                public void set64x64(String _64x64) {
                    this._64x64 = _64x64;
                }

            }
        }

    }

}
