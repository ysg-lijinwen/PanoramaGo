package com.panorama.base.iView;

import com.jointem.plugin.request.rx.BaseResponse;
import com.panorama.base.param.ReqParamsBindCloudSuccess;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by thc on 2017/8/22.
 */

public class BaseApi {
    public static final String HGP_PROJECT = "zyb/api/";

    public static final String BIND_CLOUD_SUCCESS = "push/public/bindCloudSuccess";


    public interface GTCidCloud{
        /**
         * 上送个推cid
         */
        @POST(HGP_PROJECT + BIND_CLOUD_SUCCESS)
        Flowable<BaseResponse<Object>> bindCloudCuccess(@Body ReqParamsBindCloudSuccess params);
    }
}
