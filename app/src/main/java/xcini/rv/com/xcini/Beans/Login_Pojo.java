package xcini.rv.com.xcini.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 28/12/17.
 */

public class Login_Pojo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("lonlat")
    @Expose
    private Object lonlat;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("phone_verified")
    @Expose
    private Boolean phoneVerified;
    @SerializedName("email_verified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("insurences")
    @Expose
    private Insurences insurences;
    @SerializedName("transportation")
    @Expose
    private Transportation transportation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getLonlat() {
        return lonlat;
    }

    public void setLonlat(Object lonlat) {
        this.lonlat = lonlat;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Insurences getInsurences() {
        return insurences;
    }

    public void setInsurences(Insurences insurences) {
        this.insurences = insurences;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public class Country {

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

    }

    public class Transportation {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("make")
        @Expose
        private String make;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("title")
        @Expose
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class Profile {

        @SerializedName("public_id")
        @Expose
        private String publicId;
        @SerializedName("license_number")
        @Expose
        private String licenseNumber;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("interests")
        @Expose
        private List<String> interests = null;

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }

        public String getLicenseNumber() {
            return licenseNumber;
        }

        public void setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

    }

    public class Insurences {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("insurences")
        @Expose
        private List<Object> insurences = null;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Object> getInsurences() {
            return insurences;
        }

        public void setInsurences(List<Object> insurences) {
            this.insurences = insurences;
        }
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

    public class Image {

        @SerializedName("attachment_content_type")
        @Expose
        private String attachmentContentType;
        @SerializedName("attachment_file_size")
        @Expose
        private Integer attachmentFileSize;
        @SerializedName("attachment_updated_at")
        @Expose
        private String attachmentUpdatedAt;
        @SerializedName("urls")
        @Expose
        private Urls urls;

        public String getAttachmentContentType() {
            return attachmentContentType;
        }

        public void setAttachmentContentType(String attachmentContentType) {
            this.attachmentContentType = attachmentContentType;
        }

        public Integer getAttachmentFileSize() {
            return attachmentFileSize;
        }

        public void setAttachmentFileSize(Integer attachmentFileSize) {
            this.attachmentFileSize = attachmentFileSize;
        }

        public String getAttachmentUpdatedAt() {
            return attachmentUpdatedAt;
        }

        public void setAttachmentUpdatedAt(String attachmentUpdatedAt) {
            this.attachmentUpdatedAt = attachmentUpdatedAt;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public class Urls {

            @SerializedName("medium")
            @Expose
            private String medium;
            @SerializedName("original")
            @Expose
            private String original;
            @SerializedName("detail_image")
            @Expose
            private String detailImage;

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getDetailImage() {
                return detailImage;
            }

            public void setDetailImage(String detailImage) {
                this.detailImage = detailImage;
            }

        }
    }
}
