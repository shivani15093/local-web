package com.autify.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class MetaDataModel {
    String site;
    int num_links;
    int images;
    Date last_fetch;
}
