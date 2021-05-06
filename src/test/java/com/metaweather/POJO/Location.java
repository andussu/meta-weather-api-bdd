
package com.metaweather.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Location {

    @SerializedName("consolidated_weather")
    @Expose
    private List<ConsolidatedWeather> consolidatedWeather = null;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("sun_rise")
    @Expose
    private String sunRise;
    @SerializedName("sun_set")
    @Expose
    private String sunSet;
    @SerializedName("timezone_name")
    @Expose
    private String timezoneName;
    @SerializedName("parent")
    @Expose
    private Parent parent;
    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("woeid")
    @Expose
    private Integer woeid;
    @SerializedName("latt_long")
    @Expose
    private String lattLong;
    @SerializedName("timezone")
    @Expose
    private String timezone;

}
