package com.course.udacity.android.worldofat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class GettyImageModel {

    @SerializedName("result_count")
    @Expose
    public Integer resultCount;
    @SerializedName("images")
    @Expose
    public List<Image> images = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    @Parcel
    public static class DisplaySize {

        @SerializedName("is_watermarked")
        @Expose
        public Boolean isWatermarked;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("uri")
        @Expose
        public String uri;

        public Boolean getIsWatermarked() {
            return isWatermarked;
        }

        public void setIsWatermarked(Boolean isWatermarked) {
            this.isWatermarked = isWatermarked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

    }
    @Parcel
    public static class Image {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("asset_family")
        @Expose
        public String assetFamily;
        @SerializedName("caption")
        @Expose
        public String caption;
        @SerializedName("collection_code")
        @Expose
        public String collectionCode;
        @SerializedName("collection_id")
        @Expose
        public Integer collectionId;
        @SerializedName("collection_name")
        @Expose
        public String collectionName;
        @SerializedName("display_sizes")
        @Expose
        public List<DisplaySize> displaySizes = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAssetFamily() {
            return assetFamily;
        }

        public void setAssetFamily(String assetFamily) {
            this.assetFamily = assetFamily;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getCollectionCode() {
            return collectionCode;
        }

        public void setCollectionCode(String collectionCode) {
            this.collectionCode = collectionCode;
        }

        public Integer getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(Integer collectionId) {
            this.collectionId = collectionId;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }

        public List<DisplaySize> getDisplaySizes() {
            return displaySizes;
        }

        public void setDisplaySizes(List<DisplaySize> displaySizes) {
            this.displaySizes = displaySizes;
        }

    }


}