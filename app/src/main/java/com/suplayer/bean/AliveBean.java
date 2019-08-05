package com.suplayer.bean;

import java.io.Serializable;

/**
 * Created by Wisn on 2019-08-05 10:37.
 */
public class AliveBean  implements Serializable {
    public String name;
    public String url;

    public AliveBean(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
