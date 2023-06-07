package com.kaarel.photocollection.model;

import lombok.Data;

@Data
public
class PhotoResponse {
    public int albumId;
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;
}
