package com.dipeppscc.cov_trecker.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit = null;
        public static apiinterface getapiinterface(){
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiinterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(apiinterface.class);

    }

}
