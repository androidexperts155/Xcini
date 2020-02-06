package xcini.rv.com.xcini.Beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Kyra on 1/11/2016.
 */
public class PlacePredictions_Pojo {

    @SerializedName("status")
    String status;

    @SerializedName("predictions")
    ArrayList<Predictions> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Predictions> getPredictions() {
        return predictions;
    }

    public void setPredictions(ArrayList<Predictions> predictions) {
        this.predictions = predictions;
    }

    public class Predictions {

        @SerializedName("description")
        String description;

        @SerializedName("id")
        String id;

        @SerializedName("place_id")
        String place_id;
        @SerializedName("reference")
        String reference;

        @SerializedName("matched_substrings")
        ArrayList<MatchedSubstrings> matched_substrings;

        @SerializedName("structured_formatting")
        PlacePredictions_Pojo.Predictions.StructuredFormatting structured_formatting;

        @SerializedName("terms")
        ArrayList<Terms> terms;
        @SerializedName("types")
        ArrayList<String> types;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public ArrayList<MatchedSubstrings> getMatched_substrings() {
            return matched_substrings;
        }

        public void setMatched_substrings(ArrayList<MatchedSubstrings> matched_substrings) {
            this.matched_substrings = matched_substrings;
        }

        public StructuredFormatting getStructured_formatting() {
            return structured_formatting;
        }

        public void setStructured_formatting(StructuredFormatting structured_formatting) {
            this.structured_formatting = structured_formatting;
        }

        public ArrayList<Terms> getTerms() {
            return terms;
        }

        public void setTerms(ArrayList<Terms> terms) {
            this.terms = terms;
        }

        public ArrayList<String> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<String> types) {
            this.types = types;
        }

        public class MatchedSubstrings {
            @SerializedName("length")
            String length;

            @SerializedName("offset")
            String offset;

            public String getLength() {
                return length;


            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getOffset() {
                return offset;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }
        }

        public class StructuredFormatting {
            @SerializedName("main_text")
            String main_text;

            @SerializedName("main_text_matched_substrings")
            ArrayList<MainTextMatchedSubStrings> main_text_matched_substrings;
            @SerializedName("secondary_text")
            String secondary_text;

            public String getMain_text() {
                return main_text;
            }

            public void setMain_text(String main_text) {
                this.main_text = main_text;
            }

            public ArrayList<MainTextMatchedSubStrings> getMain_text_matched_substrings() {
                return main_text_matched_substrings;
            }

            public void setMain_text_matched_substrings(ArrayList<MainTextMatchedSubStrings> main_text_matched_substrings) {
                this.main_text_matched_substrings = main_text_matched_substrings;
            }

            public String getSecondary_text() {
                return secondary_text;
            }

            public void setSecondary_text(String secondary_text) {
                this.secondary_text = secondary_text;
            }

            public class MainTextMatchedSubStrings {
                @SerializedName("length")
                String length;

                @SerializedName("offset")
                String offset;

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }

                public String getOffset() {
                    return offset;
                }

                public void setOffset(String offset) {
                    this.offset = offset;
                }
            }
        }

        public class Terms {
            @SerializedName("offset")
            String offset;

            @SerializedName("value")
            String value;

            public String getOffset() {
                return offset;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

}
